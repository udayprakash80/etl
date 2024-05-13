package com.saras.etl.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class FirstDatabaseConfig {

    @Bean(name = "firstDataSource")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5433/postgres")
                .username("postgres")
                .password("mypassword")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean(name = "firstJdbcTemplate")
    public JdbcTemplate firstJdbcTemplate(DataSource firstDataSource) {
        return new JdbcTemplate(firstDataSource);
    }
}

