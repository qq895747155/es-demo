package com.es.esdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.es.esdemo.repository")
public class EsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsdemoApplication.class, args);
    }


}
