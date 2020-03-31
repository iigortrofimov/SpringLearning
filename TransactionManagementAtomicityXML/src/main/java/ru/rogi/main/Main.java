package ru.rogi.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.BadSqlGrammarException;

@SpringBootApplication
@ImportResource("beans.xml")
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    ApplicationContext context;

    public void run(String... strings) throws Exception {
        ProductUtils productUtils = context.getBean(ProductUtils.class);

        try{
            productUtils.addTwo();
        }catch (BadSqlGrammarException gr){
            System.out.println(gr);
        }

        productUtils.print();

        System.out.println(productUtils.getClass());
    }
}