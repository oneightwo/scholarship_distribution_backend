package com.oneightwo.scholarship_distribution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Config {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/scholarship_distribution");
        dataSource.setUsername("postgres");
        dataSource.setPassword("lumia640");
        return dataSource;
    }
}
