package ru.rogi.springcource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.rogi.springcource.config.SpringConfig;
import ru.rogi.springcource.music.Music;

import java.util.List;


public class TestSpring{

    public static void main(String[] args) {
/*        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationcontextannot.xml");*/


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        MusicCenter center = context.getBean("musicCenter", MusicCenter.class);
        center.switchOn();
        List<Music> genres = context.getBean(List.class);
        System.out.println(genres.toString());

        context.close();
    }
}
