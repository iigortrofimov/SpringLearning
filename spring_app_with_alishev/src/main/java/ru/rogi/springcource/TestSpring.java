package ru.rogi.springcource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.rogi.springcource.music.Genre;
import ru.rogi.springcource.musicplayer.MusicPlayer;


public class TestSpring{

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationcontextannot.xml");
        //MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        MusicCenter center = context.getBean("musicCenter", MusicCenter.class);
        //player.playMusic();
        center.switchOn(Genre.CLASSICAL);
        center.switchOn(Genre.ROCK);

        context.close();
    }
}
