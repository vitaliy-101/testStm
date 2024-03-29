package com.example.teststm.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    public JdbcTemplate template(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
