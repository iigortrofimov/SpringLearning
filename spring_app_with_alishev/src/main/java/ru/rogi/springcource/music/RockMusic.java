package ru.rogi.springcource.music;

public class RockMusic implements Music {
    @Override
    public void play() {
        System.out.println("Playing Rock music .. ");
    }
}
