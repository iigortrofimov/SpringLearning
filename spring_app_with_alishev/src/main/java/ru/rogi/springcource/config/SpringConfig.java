package ru.rogi.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.rogi.springcource.music.ClassicMusic;
import ru.rogi.springcource.music.JazzMusic;
import ru.rogi.springcource.music.Music;
import ru.rogi.springcource.music.RockMusic;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("ru.rogi.springcource")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
    @Autowired
    private ClassicMusic classic;

    @Autowired
    private RockMusic rock;

    @Autowired
    private JazzMusic jazz;

    @Bean
    public List<Music> musicList(){
        List<Music> music = new ArrayList<>();
        music.add(classic);
        music.add(rock);
        music.add(jazz);
        return music;
    }
}
