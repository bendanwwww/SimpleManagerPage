package com.manager;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @author zhangyt
 */
@EnableScheduling
@SpringBootApplication
public class IpsApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(IpsApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
