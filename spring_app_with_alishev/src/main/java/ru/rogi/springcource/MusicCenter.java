package ru.rogi.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rogi.springcource.music.Genre;
import ru.rogi.springcource.musicplayer.MusicPlayer;

@Component
public class MusicCenter {
    private MusicPlayer player;
    private String brandName;

    @Autowired
    public MusicCenter(MusicPlayer player){
        this.player = player;
        brandName = "Pioner";
    }

    public void switchOn(Genre genre){
        System.out.println("Music Center " + brandName + " turned ON !");
        player.playMusic(genre);
        System.out.println("Music Center " + brandName + " turned OFF !\n");

    }
}
