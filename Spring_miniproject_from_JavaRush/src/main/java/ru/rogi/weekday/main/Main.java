package ru.rogi.weekday.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rogi.animals.entities.Cat;
import ru.rogi.animals.entities.Dog;
import ru.rogi.animals.entities.Parrot;
import ru.rogi.weekday.WeekDay;
import ru.rogi.weekday.configs.MyConfig;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        WeekDay weekDay = context.getBean(WeekDay.class);
        System.out.println("Today is " + weekDay.getWeekDayName());

        Cat cat = context.getBean(Cat.class);
        Dog dog = (Dog) context.getBean("dog");
        Parrot parrot = context.getBean("Kesha", Parrot.class);
        System.out.println(cat.getName() + ", " + dog.getName() + ", " + parrot.getName());

    }
}
