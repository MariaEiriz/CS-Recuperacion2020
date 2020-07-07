package com.example.tetris;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.core.content.ContextCompat.startActivity;


public class Juego extends View implements View.OnClickListener {

    private ImageButton botonDcha, botonBajar, botonIzda, botonRotar;
    private TextView puntuacion, nivel;
    private MainActivity mainActivity;
    private Tablero tablero;
    private ArrayList<Pieza> listaPiezas;
    private Random random = new Random();
    private int puntos = 0;
    private int nivelvar = 1;
    private Timer timer = new Timer();
    private List<Integer> filasPorBorrar;
    private int timerPeriod = 250;
    private VentanaNext ventana;
    private int contadorRomper = 0;
    private int restoContador;
    private int alturaVariable;
    private int modo;
    private Pieza troll;
    private int restoPieza;
    private Pieza auxTroll;

    public Juego(Context context, Tablero tablero, VentanaNext ventana, int modo) {
        super(context);
        this.mainActivity = (MainActivity) context;
        this.tablero = tablero;
        this.ventana = ventana;
        this.modo = modo;
        this.listaPiezas = tablero.getListaPiezas();
        botonRotar = mainActivity.getBotonRotar();
        botonDcha = mainActivity.getBotonDcha();
        botonBajar = mainActivity.getBotonBajar();
        botonIzda = mainActivity.getBotonIzda();
        puntuacion = mainActivity.getPuntos();
        nivel = mainActivity.getNivel();

        puntuacion.append(" 0");
        nivel.append(" 1");


        botonDcha.setOnClickListener(this);
        botonBajar.setOnClickListener(this);
        botonIzda.setOnClickListener(this);
        botonRotar.setOnClickListener(this);
        if (modo == 0) {
            loopClasico();
        } else {
            gameLoop();
        }
    }

    public void loopClasico() {
        ventana.runVentanaNext(listaPiezas.get(1));
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {
                        tablero.ponerPieza(tablero.getPieza());
                        if (!tablero.puedeMoverse(tablero.getPieza(), 0, 1, false) && tablero.getPieza().getAltura() == 0) {
                            timer.cancel();
                            mainActivity.gameOver(puntos, modo);
                        } else {
                            if (tablero.puedeMoverse(tablero.getPieza(), 0, 1, false)) {
                                tablero.moverPiezas(tablero.getPieza(), 'a');
                                timer.cancel();
                                timer = new Timer();
                                loopClasico();
                            } else {
                                filasPorBorrar = tablero.detectarFilas(null);
                                tablero.borrarPieza();
                                setPuntos(filasPorBorrar.size() * 30);
                                puntuacion.setText("" + puntos);
                                cambiarColorLinea(filasPorBorrar.size());
                                tablero.ponerPieza(tablero.getPieza());
                                tablero.generarPieza(0);
                                ventana.runVentanaNext(listaPiezas.get(1));
                                ventana.invalidate();

                            }
                            invalidate();
                        }
                    }
                });
            }
        }, 500, timerPeriod);
    }

    public void gameLoop() {
        ventana.runVentanaNext(listaPiezas.get(1));
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        tablero.ponerPieza(tablero.getPieza());
                        checkComerTablero();
                        if (!tablero.puedeMoverse(tablero.getPieza(), 0, 1, false) && tablero.getPieza().getAltura() - 2 <= alturaVariable) {
                            timer.cancel();
                            mainActivity.gameOver(puntos, modo);
                        } else {
                            contadorRomper++;
                            restoContador = contadorRomper % 50;
                            restoPieza = contadorRomper % 30;
                            if (restoContador == 0) {
                                alturaVariable += 2;
                            }
                            if (restoPieza == 0) {
                                piezaTroll(alturaVariable);
                            }
                            if (tablero.puedeMoverse(tablero.getPieza(), 0, 1, false)) {
                                tablero.moverPiezas(tablero.getPieza(), 'a');
                                if ((tablero.puedeMoverse(troll, 0, 1, false))) {
                                    tablero.moverPiezas(troll, 'a');
                                } else {
                                    troll = null;
                                }
                                checkComerTablero();
                                timer.cancel();
                                timer = new Timer();
                                gameLoop();
                            } else {
                                filasPorBorrar = tablero.detectarFilas(troll);
                                tablero.borrarPieza();
                                setPuntos(filasPorBorrar.size() * 30);
                                puntuacion.setText("" + puntos);
                                cambiarColorLinea(filasPorBorrar.size());
                                checkSiguienteCont();
                                checkComerTablero();
                                ventana.runVentanaNext(listaPiezas.get(1));
                                ventana.invalidate();
                            }
                            invalidate();
                        }
                    }
                });
            }
        }, 1000, timerPeriod);
    }

    public void piezaTroll(int altura) {
        int n = (int) (Math.random() * 2);
        if (n == 1) {
            troll = new Pieza(10, altura);
        } else if (n == 0) {
            troll = new Pieza(9, altura);
        }
        tablero.ponerPieza(troll);
    }

    public void checkComerTablero() {
        if (restoContador == 0) {
            while ((tablero.getPieza().getAltura() < alturaVariable) && (troll.getAltura() < alturaVariable)) {
                tablero.moverPiezas(tablero.getPieza(), 'a');
                tablero.moverPiezas(troll, 'a');
            }
            tablero.comerTablero(alturaVariable);
        }
    }

    public void checkSiguienteCont() {
        if ((contadorRomper + 1) % 10 == 0) {
            tablero.generarPieza(alturaVariable + 4);
        } else {
            tablero.generarPieza(alturaVariable + 2);
        }
    }

    public void cambiarColorLinea(int i) {
        if (i != 0) {
            if (i == 1) {
                tablero.CambiarColores1Linea();
            } else {
                tablero.CambiarColoresMultiLinea();
            }
            filasPorBorrar.clear();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Pintamos el tablero back
        Paint pincel = new Paint();

        for (int x = 0; x < tablero.getAnchoTablero(); x++) {
            for (int y = 0; y < tablero.getAlturaTablero(); y++) {

                int color = tablero.parseaColor(x, y);
                pincel.setColor(color);
                canvas.drawRect(x * getMeasuredWidth() / 10, y * getMeasuredHeight() / 20, x * getMeasuredWidth() + getMeasuredWidth() / 10,
                        y * getMeasuredHeight() + getMeasuredHeight() / 20, pincel);
            }
        }

        //Pintamos el tablero front
        Paint pBorde = new Paint();
        pBorde.setStyle(Paint.Style.STROKE);
        pBorde.setColor(Color.BLACK);
        pBorde.setStrokeWidth(2);
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 20; y++) {
                canvas.drawLine((x + 1) * getMeasuredWidth() / tablero.getAnchoTablero(), 0, (x + 1) * getMeasuredWidth() / tablero.getAnchoTablero(), getMeasuredHeight(), pBorde);
                canvas.drawLine(0, (y + 1) * getMeasuredHeight() / tablero.getAlturaTablero(), getMeasuredWidth(), (y + 1) * getMeasuredHeight() / tablero.getAlturaTablero(), pBorde);
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.botonDcha:
                tablero.moverPiezas(tablero.getPieza(), 'd');
                invalidate();
                break;
            case R.id.botonBajar:
                tablero.moverPiezas(tablero.getPieza(), 'a');
                tablero.moverPiezas(troll, 'a');
                setPuntos(1);
                puntuacion.setText("" + puntos);
                invalidate();
                break;
            case R.id.botonIzda:
                tablero.moverPiezas(tablero.getPieza(), 'i');
                invalidate();
                break;
            case R.id.botonRotar:
                Pieza p = tablero.getPieza();
                if (p.idColor != 1) {
                    tablero.borrarPieza(p);
                    tablero.comprobarRotar(p);
                    tablero.ponerPieza(p);
                }
                invalidate();
                break;
        }
    }

    public void setPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public int getNivel() {
        return this.nivelvar;
    }

}


