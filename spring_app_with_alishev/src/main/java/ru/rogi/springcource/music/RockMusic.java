package ru.rogi.springcource.music;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

//@Component("rock")
public class RockMusic implements Music {

    private List<Song> songList = new ArrayList<>();

    @PostConstruct
    public void doMyInit(){
        System.out.println("Initialization of rock music..");
        Song song1 = new Song("Author1", "rock song № 1");
        Song song2 = new Song("Author2", "rock song № 2");
        Song song3 = new Song("Author3", "rock song № 3");
        songList.add(song1);
        songList.add(song2);
        songList.add(song3);
        System.out.println("Initialization of rock music completed.\n");
    }

    @PreDestroy
    public void doMyDestroy(){
        System.out.println("destruction rock bean\n");
    }

    //@Override
    public void play(int index) {
        System.out.println("Rock music: ");
        songList.get(index).platSong();
        System.out.println();
    }

    //@Override
    public List<Song> getList() {
        return songList;
    }
}
