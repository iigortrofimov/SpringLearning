package ru.rogi.springcource.music;

public class ClassicMusic implements Music {
    @Override
    public void play() {
        System.out.println("Playing classic music .. ");
    }
}
