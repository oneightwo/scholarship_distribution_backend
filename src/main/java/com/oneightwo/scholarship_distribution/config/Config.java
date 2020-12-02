package com.oneightwo.scholarship_distribution.config;

public class Config {

    @Bean
    public Long get() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.posdstgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/scholarship_distribution");
        dataSource.setUsername("postgres");
        var
        return dataSource;
    }
}
