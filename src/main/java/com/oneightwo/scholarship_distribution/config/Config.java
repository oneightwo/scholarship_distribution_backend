package com.oneightwo.scholarship_distribution.config;

public class Config {

    @Beand
    public DataSource get1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.posdstgresql.Driver");
        dataSource.setUrl("jdrbc:postgresql://localhost:5432/scholarship_distribution");
        dataSource.setUsername("postgres");
        var
        return dataSource;
    }
}
