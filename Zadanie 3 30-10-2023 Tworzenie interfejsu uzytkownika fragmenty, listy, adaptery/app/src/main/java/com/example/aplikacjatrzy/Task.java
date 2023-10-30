package com.example.aplikacjatrzy;

import java.util.Date;
import java.util.UUID;

public class Task {
    private UUID id;
    private  String name;
    private Date date;
    private boolean done;

    public Task(){
        id = UUID.randomUUID();
        date = new Date();
    }


    public void setName(String toString) {
        this.name = toString;
    }

    public Object getDate() {
        return date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean isChecked) {
        this.done = isChecked;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
