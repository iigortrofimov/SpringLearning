package ru.rogi.beans;

public class Client {
    private String id;
    private String fullName;
    private String greeting;

    public Client(String id, String name) {
        this.id = id;
        this.fullName = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }
}
