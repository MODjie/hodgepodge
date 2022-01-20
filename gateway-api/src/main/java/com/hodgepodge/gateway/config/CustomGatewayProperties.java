package com.hodgepodge.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: CustomGatewayProperties
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月20日
 * @since 1.8
 */
@ConfigurationProperties(CustomGatewayProperties.PREFIX)
public class CustomGatewayProperties {

    public static final String PREFIX = "gateway";

    /**
     * 网关请求路径白名单
     */
    private List<String> whiteList = new ArrayList<>();

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}
