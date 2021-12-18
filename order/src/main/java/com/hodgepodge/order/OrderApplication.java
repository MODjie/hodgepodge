package com.hodgepodge.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 * Title: SystemApplication
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
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
