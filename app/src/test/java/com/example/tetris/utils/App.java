package com.example.tetris.utils;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tetris.Inicio;
import com.example.tetris.Juego;
import com.example.tetris.MainActivity;
import com.example.tetris.OpcionesActivity;
import com.example.tetris.Pieza;
import com.example.tetris.Tablero;
import com.example.tetris.VentanaNext;

import java.util.ArrayList;

public class App extends AppCompatActivity {
    private Inicio inicio;
    private MainActivity main_activity;
    private Juego juego;
    private Tablero tablero;
    private Pieza siguientePieza;
    private OpcionesActivity settings;

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

    public void startModoClasico(){
        int modo = 0;
        startJuego(modo);
    }
    public void startMuerteSubita(){
        int modo = 1;
        startJuego(modo);
    }

    public boolean isStartModoClasico(){
        if (this.juego != null && this.juego.getModo() ==0){
            return true;
        }
        return false;
    }
    public boolean isStartMuerteSubita(){
        if (this.juego != null && this.juego.getModo() ==1){
            return true;
        }
        return false;
    }

    public void startJuego(int modo){
        this.main_activity = new MainActivity();
        this.main_activity.setBotonDcha(null);
        this.main_activity.setBotonIzda(null);
        this.main_activity.setBotonBajar(null);
        this.main_activity.setBotonRotar(null);
        this.main_activity.setPuntosTextView(null);
        this.main_activity.setNivelTextView(null);
        Pieza p = new Pieza(0,0);
        VentanaNext siguientePieza = new VentanaNext(this.main_activity, this.main_activity.getVentana(), p);
        this.juego  = new Juego(this.main_activity, this.main_activity.getTablero(), siguientePieza,modo);
    }
    public void colocarUltimaPieza(){
        this.tablero = this.juego.getTablero();
        this.siguientePieza = new Pieza(1,0);
        this.siguientePieza.y1 = 0;
        this.siguientePieza.y2 = 0;
        this.siguientePieza.y3 = 0;
        this.siguientePieza.y4 = 0;
        ArrayList<Pieza> piezasTablero = new ArrayList<>();
        piezasTablero.add(this.siguientePieza);
        this.tablero.setListaPiezas(piezasTablero);
    }
    public boolean isGameOver(){
        if (/*!this.tablero.puedeMoverse(this.tablero.getPieza(), 0, 1, false)
                && */this.tablero.getPieza().getAltura() == 0) {
            return true;
        }
        return false;
    }
    public void startSettings(){
        this.settings = new OpcionesActivity();
    }

    public void setColors(String sCuadrado, String sZ, String sI, String sT, String sS, String sL, String sJ) {
        if (sCuadrado!=null  && isValidColor(sCuadrado)){
            this.tablero.setColorCuadrado(intColor(sCuadrado));
        }
        if (sZ!=null  && isValidColor(sZ)) {
            this.tablero.setColorZPieza(intColor(sZ));
        }
        if (sI!=null  && isValidColor(sI)) {
            this.tablero.setColorIPieza(intColor(sI));
        }
        if (sT!=null  && isValidColor(sT)) {
            this.tablero.setColorTPieza(intColor(sT));
        }
        if (sS!=null  && isValidColor(sS)) {
            this.tablero.setColorSPieza(intColor(sS));
        }
        if (sL!=null  && isValidColor(sL)) {
            this.tablero.setColorLPieza(intColor(sL));
        }
        if (sJ!=null  && isValidColor(sJ)) {
            this.tablero.setColorJPieza(intColor(sJ));
        }
    }
    public int intColor(String color){
        int index = -1;
        switch (color){
            case "Pink":
                index=1;
                break;
            case "Light Blue":
                index =2;
                break;
            case "Dark Blue":
                index =3;
                break;
            case "Green":
                index =4;
                break;
            case "Orange":
                index =5;
                break;
            case "Yellow":
                index =6;
                break;
            case "Red":
                index =7;
                break;
            default:break;
        }
        return index;
    }
    public boolean isValidColor(String color){
        if (color == "Pink" || color == "Light Blue" ||color == "Dark Blue" ||color == "Green" ||color == "Orange" ||
                color == "Yellow" ||color == "Red" ){
            return true;
        }
        return false;
    }

    public boolean isSettingsChanged() {
        boolean cambio = false;
        if (this.tablero.getColorCuadrado() !=1){
            cambio = true;
        }
        if (this.tablero.getColorZPieza() !=2 ){
            cambio = true;
        }
        if (this.tablero.getColorIPieza() !=3 ){
            cambio = true;
        }
        if (this.tablero.getColorTPieza() !=4){
            cambio = true;
        }
        if (this.tablero.getColorSPieza() !=5){
            cambio = true;
        }
        if (this.tablero.getColorLPieza() !=6){
            cambio = true;
        }
        if (this.tablero.getColorJPieza() !=7){
            cambio = true;
        }
        return cambio;
    }
}
