package ru.rogi.springcource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.rogi.springcource.music.Music;
import ru.rogi.springcource.musicplayer.MusicPlayer;


public class TestSpring{

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationcontext.xml");
        MusicPlayer player = context.getBean("musicPlayer", MusicPlayer.class);
        player.playMusic();
        System.out.println(player.getBrandName() + " " + player.getVolume());

        context.close();
    }
}
