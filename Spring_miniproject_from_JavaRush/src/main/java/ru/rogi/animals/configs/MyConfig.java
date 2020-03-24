package ru.rogi.animals.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rogi.animals.entities.Cat;
import ru.rogi.animals.entities.Dog;
import ru.rogi.animals.entities.Parrot;

@Configuration
public class MyConfig {

    @Bean
    public Cat getCat(Parrot parrot){
        Cat cat = new Cat();
        cat.setName(parrot.getName()+"-killer");
        return cat;
    }

    @Bean
    public Dog getDog(){
        return new Dog();
    }

    @Bean("Kesha")
    public Parrot getParrot(){
        return new Parrot();
    }
}
