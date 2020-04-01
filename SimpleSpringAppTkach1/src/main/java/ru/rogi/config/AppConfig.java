package ru.rogi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import ru.rogi.beans.Client;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Autowired
    private Environment environment;


    @Bean
    public Client client(){
        Client client = new Client(environment.getRequiredProperty("id"), environment.getRequiredProperty("name"));
        client.setGreeting("greeting");
        return client;
    }

    @Bean
    public Date newDate(){
        return new Date();
    }

    @Bean
    public DateFormat dateFormat(){
        return DateFormat.getDateInstance();
    }




}
