package ru.rogi.springcource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.rogi.springcource.MusicCenter;
import ru.rogi.springcource.music.ClassicMusic;
import ru.rogi.springcource.music.JazzMusic;
import ru.rogi.springcource.music.Music;
import ru.rogi.springcource.music.RockMusic;
import ru.rogi.springcource.musicplayer.MusicPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
//@ComponentScan("ru.rogi.springcource")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

    @Bean
    public ClassicMusic classicMusic(){
        return new ClassicMusic();
    }

    @Bean
    public RockMusic rockMusic(){
        return new RockMusic();
    }

    @Bean
    public JazzMusic jazzMusic(){
        return new JazzMusic();
    }

    @Bean
    public MusicPlayer musicPlayer(){
        return new MusicPlayer(musicList());
    }

    @Bean
    public List<Music> musicList(){
        return Arrays.asList(classicMusic(), rockMusic(), jazzMusic());
    }

    @Bean
    public MusicCenter musicCenter(){
        return new MusicCenter(musicPlayer());
    }
}
