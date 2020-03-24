package ru.rogi.animals.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rogi.animals.configs.MyConfig;
import ru.rogi.animals.entities.Cat;
import ru.rogi.animals.entities.Dog;
import ru.rogi.animals.entities.Parrot;

public class Main {
    public static void main(String[] args) {
        // создаем пустой спринговый контекст, который будет искать свои бины по аннотациям в указанном пакете
        //ApplicationContext context = new AnnotationConfigApplicationContext("ru.rogi.animals");
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class); // Если много, то указываем пакет, где лежат конфиги

        Cat cat = context.getBean(Cat.class);
        //Dog dog = (Dog) context.getBean("dog");
        Dog dog = (Dog) context.getBean("getDog");
        Parrot parrot = context.getBean("Kesha", Parrot.class);
        System.out.println(cat.getName() + ", " + dog.getName() + ", " + parrot.getName());
    }
}
