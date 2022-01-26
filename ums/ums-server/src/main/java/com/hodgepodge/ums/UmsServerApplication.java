package com.hodgepodge.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * Title: UmsServerApplication
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2021年12月13日
 * @since 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.hodgepodge.ums.client")
public class UmsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmsServerApplication.class, args);
    }

}
