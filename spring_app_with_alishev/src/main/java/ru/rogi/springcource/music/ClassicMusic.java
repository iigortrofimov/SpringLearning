package ru.rogi.springcource.music;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component("classic")
public class ClassicMusic implements Music {

    private List<Song> songList = new ArrayList<>();

    @PostConstruct
    public void doMyInit(){
        System.out.println("Initialization of classic music..");
        Song song1 = new Song("Author1", "classic song № 1");
        Song song2 = new Song("Author2", "classic song № 2");
        Song song3 = new Song("Author3", "classic song № 3");
        songList.add(song1);
        songList.add(song2);
        songList.add(song3);
        System.out.println("Initialization of classic music completed.\n");
    }

    @PreDestroy
    public void doMyDestroy(){
        System.out.println("destruction classic bean\n");
    }

    @Override
    public void play(int index) {
        System.out.println("Classic music: ");
        System.out.println("Playing.." + songList.get(index));
        System.out.println();
    }


}
