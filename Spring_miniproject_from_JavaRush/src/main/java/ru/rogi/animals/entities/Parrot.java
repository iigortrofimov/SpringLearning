package ru.rogi.animals.entities;

import org.springframework.stereotype.Component;

@Component("Kesha")
public class Parrot {
    private String name = "Kesha";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
