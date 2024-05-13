package com.saras.etl.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SecondDatabaseConfig {

    @Bean(name = "secondDataSource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5433/postgres1")
                .username("postgres")
                .password("mypassword")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean(name = "secondJdbcTemplate")
    public JdbcTemplate secondJdbcTemplate(DataSource secondDataSource) {
        return new JdbcTemplate(secondDataSource);
    }
}
