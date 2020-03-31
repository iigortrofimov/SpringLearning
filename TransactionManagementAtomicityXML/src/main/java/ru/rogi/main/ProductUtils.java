package ru.rogi.main;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public class ProductUtils {
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    JdbcTemplate jdbcTemplate;

    public void addNew(String name, Double price){
        jdbcTemplate.update("INSERT INTO Product VALUES(?,?)", name, price);
    }

    public void causeAnException(){
        jdbcTemplate.update("INSERT INTO ProductX");
    }

    @Transactional
    public void addTwo(){
        addNew("Keyboard", 3.12);
        causeAnException();
        addNew("Tablet", 32.3);
    }

    public void print(){
        for(Map<String, Object> m : jdbcTemplate.queryForList("SELECT * FROM Product")){
            System.out.println(m.get("name") + " " + m.get("price"));
        }
    }
}

