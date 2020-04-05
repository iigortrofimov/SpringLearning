package ru.rogi.springcource.music;

public class Song {

    private String author;
    private String songName;

    public Song(String author, String songName) {
        this.author = author;
        this.songName = songName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "Song{" +
                "author='" + author + '\'' +
                ", songName='" + songName + '\'' +
                '}';
    }

    public void platSong(){
        System.out.println("Playing " + author + ", " + songName);
    }
}
