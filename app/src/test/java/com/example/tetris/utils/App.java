package com.example.tetris.utils;

import com.example.tetris.Inicio;

public class App {
    private Inicio inicio;
    public App(){}
    public void start(){
        this.inicio = new Inicio();
    }
    public boolean isStarted(){
        if (inicio != null){
            return true;
        }
        return false;
    }
}
