package com.ningmeng.manage_cms_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 炫龙 on 2020/2/17.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.ningmeng.framework.domain.cms")//扫描实体类
@ComponentScan(basePackages={"com.ningmeng.framework"})//扫描common下的所有类
@ComponentScan(basePackages={"com.ningmeng.manage_cms_client"})
public class ManageCmsClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClientApplication.class,args);
    }
}
