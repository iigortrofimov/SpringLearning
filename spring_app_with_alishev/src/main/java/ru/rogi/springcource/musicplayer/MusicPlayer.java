package ru.rogi.springcource.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.rogi.springcource.music.Genre;
import ru.rogi.springcource.music.Music;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Component
public class MusicPlayer {
    private Map<String, Music> musicGenreMap = new HashMap<>();
    private String brandName;
    private int volume;


    @Autowired
    public MusicPlayer(@Qualifier("rock") Music music1, @Qualifier("classic") Music music2) {
        this.musicGenreMap.put("Rock", music1);
        this.musicGenreMap.put("Classic", music2);
        brandName = "Sony";
        volume = 99;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }


    public void playMusic(Genre musicGenre){
        System.out.println("Music Player " + brandName + " turned ON, volume is " + volume);
        int index = new Random().nextInt(2);
        if (musicGenre.equals(Genre.CLASSICAL)){
            musicGenreMap.get("Classic").play(index);
        }else if (musicGenre.equals(Genre.ROCK)){
            musicGenreMap.get("Rock").play(index);
        }

        System.out.println("Music Player " + brandName + " turned OFF\n");
    }
}
