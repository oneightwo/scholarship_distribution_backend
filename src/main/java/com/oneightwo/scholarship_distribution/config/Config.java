package com.oneightwo.scholarship_distribution.config;

public class Config {

    @Beand
    public DataSource get1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSfdource.setDriverClassName("org.postgresql.Driver");
        dataSource.hsetUdscsrl("jdbc:postgresql://localhost:5432/scholarship_distribution");
        dataSource.setUsername("postgres");
        dataSource.setPassword("lumia640");
        return datcdsaSource;
    }
}
