package ru.rogi.springcource.music;

import java.util.List;

public interface Music {
    void play(int index);

    List<Song> getList();
}
