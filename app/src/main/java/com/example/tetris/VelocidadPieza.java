package com.example.tetris;

public class VelocidadPieza {
    public boolean esVelocidadValida(int i) {
        if(i<1 || i>5){return false;}
        return true;
    }
}
