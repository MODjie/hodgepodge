/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hodgepodge.ums.auth.config;

import com.hodgepodge.ums.auth.jose.Jwks;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.*;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.UUID;

/**
 * <p>
 * Title: 授权服务器配置
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月18日
 * @since 1.8
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        //配置授权服务器
//		OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
//				new OAuth2AuthorizationServerConfigurer<>();
        //设置授权入口
//		authorizationServerConfigurer
//				.authorizationEndpoint(authorizationEndpoint ->
//						authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI));

        //获取入口匹配器
//		RequestMatcher endpointsMatcher = authorizationServerConfigurer
//				.getEndpointsMatcher();
//
//		//配置安全请求相关信息
//		http
//				//设置入口匹配器
//				.requestMatcher(endpointsMatcher)
//				//自定义需要授权的请求
//				.authorizeRequests(authorizeRequests ->
//						authorizeRequests.anyRequest().authenticated()
//				)
//				//授权入口请求关闭csrf
//				.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
//				//设置授权服务器配置
//				.apply(authorizationServerConfigurer);
        //返回表单登录的过滤器链
        return http.formLogin(Customizer.withDefaults()).build();
    }

    /**
     * <p>
     * Title: 配置注册客户端仓库
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
     * @author 刘小杰
     * @date 2022年01月18日
     * @since 1.8
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        //ID需要设置撑唯一的值，否则每次启动都会新增一条clientId一样的数据
        RegisteredClient registeredClient = RegisteredClient
                .withId("hodgepodge-ui-id")
                .clientId("hodgepodge-ui")
                .clientSecret("secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://www.baidu.com")
                .scope(OidcScopes.OPENID)
                .scope("message.read")
                .scope("message.write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        JdbcRegisteredClientRepository jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
        JdbcRegisteredClientRepository.RegisteredClientParametersMapper registeredClientParametersMapper = new JdbcRegisteredClientRepository.RegisteredClientParametersMapper();
        //设置密码编码器为自定义的编码器，否则与默认的不同，会导致获取token时密码校验错误，报错：invalid_client
        registeredClientParametersMapper.setPasswordEncoder(passwordEncoder);
        jdbcRegisteredClientRepository.setRegisteredClientParametersMapper(registeredClientParametersMapper);
        jdbcRegisteredClientRepository.save(registeredClient);
        return jdbcRegisteredClientRepository;
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder().issuer("http://localhost:8001").build();
    }

    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }

    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }
}
