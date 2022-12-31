package com.springbatchexp.springbatchexpample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@ComponentScan({"com.springbatchexp"})
@SpringBootApplication
@EnableAsync
@EntityScan("com.springbatchexp.springbatchexpample")
@EnableJpaRepositories("com.springbatchexp.springbatchexpample")
@EnableJpaAuditing
public class SpringbatchexpampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbatchexpampleApplication.class, args);
    }

}
