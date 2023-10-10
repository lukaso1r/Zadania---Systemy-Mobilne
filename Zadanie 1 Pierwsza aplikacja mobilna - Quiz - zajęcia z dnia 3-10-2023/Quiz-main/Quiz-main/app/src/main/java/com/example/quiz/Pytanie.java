package com.example.quiz;

public class Pytanie {
    private int IDpytanie;
    private boolean odpowiedz;

    public Pytanie(int IDpytanie, boolean odpowiedz){
        this.IDpytanie = IDpytanie;
        this.odpowiedz = odpowiedz;
    }

    public int getIDpytanie(){
        return  IDpytanie;
    }

    public boolean getOdpowiedz(){
        return odpowiedz;
    }
}
