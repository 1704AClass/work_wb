package com.ningmeng.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 炫龙 on 2020/2/11.
 */
@SpringBootApplication
@EnableFeignClients
@EntityScan("com.ningmeng.framework.domain.cms")
@ComponentScan(basePackages={"com.ningmeng.api"})//扫描接口
@ComponentScan(basePackages="com.ningmeng.framework")//扫描common工程下的类
@ComponentScan(basePackages={"com.ningmeng.manage_cms"})//扫描本项目下的所有类
public class ManageCmsApplication {
        public static void main(String[] args) {
            SpringApplication.run(ManageCmsApplication.class,args);
        }
}
