package ru.rogi.springcource.musicplayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.rogi.springcource.music.Music;

import java.util.*;


//@Component
public class MusicPlayer {
    private List<Music> genreList;

    @Value("${musicPlayer.brandName}")
    private String brandName;

    @Value("${musicPlayer.volume}")
    private int volume;

    //@Autowired
    public MusicPlayer(@Qualifier("musicList")List<Music> musicList) {
        this.genreList = musicList;
    }

    public void playMusic(){
        System.out.println("Music Player " + brandName + " turned ON, volume is " + volume);

        int genreIndex = new Random().nextInt(genreList.size());
        int songIndex = new Random().nextInt(genreList.get(genreIndex).getList().size());
        genreList.get(genreIndex).play(songIndex);

        System.out.println("Music Player " + brandName + " turned OFF\n");
    }

    public String getBrandName() {
        return brandName;
    }

    public int getVolume() {
        return volume;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
