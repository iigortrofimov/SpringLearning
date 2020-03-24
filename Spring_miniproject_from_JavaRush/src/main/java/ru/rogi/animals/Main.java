package ru.rogi.animals;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rogi.animals.entities.Cat;
import ru.rogi.animals.entities.Dog;
import ru.rogi.animals.entities.Parrot;

public class Main {
    public static void main(String[] args) {
        // создаем пустой спринговый контекст, который будет искать свои бины по аннотациям в указанном пакете
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.rogi.animals");

        Cat cat = context.getBean(Cat.class);
        Dog dog = (Dog) context.getBean("dog");
        Parrot parrot = context.getBean("Kesha", Parrot.class);
        System.out.println(cat.getName() + ", " + dog.getName() + ", " + parrot.getName());
    }
}
