package com.saras.etl.service;

import com.saras.etl.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    private final JdbcTemplate firstJdbcTemplate;
    private final JdbcTemplate secondJdbcTemplate;

    @Autowired
    public MyService(@Qualifier("firstJdbcTemplate") JdbcTemplate firstJdbcTemplate,
                     @Qualifier("secondJdbcTemplate") JdbcTemplate secondJdbcTemplate) {
        this.firstJdbcTemplate = firstJdbcTemplate;
        this.secondJdbcTemplate = secondJdbcTemplate;
    }

    public void doSomethingWithDatabases() {
        String FirstSql = "select * from Product";
        List<Product> productList =  this.firstJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        String FirstSql = "select * from Product";

        // Use firstJdbcTemplate and secondJdbcTemplate to interact with databases
    }
}

