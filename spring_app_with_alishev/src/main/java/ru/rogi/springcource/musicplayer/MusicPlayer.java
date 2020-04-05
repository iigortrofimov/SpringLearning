package ru.rogi.springcource.musicplayer;

import ru.rogi.springcource.music.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private List<Music> musicList = new ArrayList<>();
    private String brandName;
    private int volume;

    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

    public MusicPlayer() {
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public void playMusic(){
        for (Music music: musicList){
            music.play();
        }
    }

    public String getBrandName() {
        return brandName;
    }

    public int getVolume() {
        return volume;
    }

    public List<Music> getMusicList() {
        return musicList;
    }
}
