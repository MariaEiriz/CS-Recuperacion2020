package com.example.tetris;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton botonDcha, botonBajar, botonIzda, botonRotar;
    private TextView puntosTextView, nivelTextView;
    private Juego juego;
    private Tablero tablero = new Tablero();
    private Tablero ventana = new Tablero();
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle b = this.getIntent().getExtras();
        int modo=b.getInt("MODO");

        botonDcha = (ImageButton) findViewById(R.id.botonDcha);
        botonIzda = (ImageButton) findViewById(R.id.botonIzda);
        botonBajar = (ImageButton) findViewById(R.id.botonBajar);
        botonRotar = (ImageButton) findViewById(R.id.botonRotar);
        puntosTextView = (TextView) findViewById(R.id.puntosText);
        nivelTextView = (TextView) findViewById(R.id.nivelText);
        Pieza p = new Pieza(0,0);

        VentanaNext siguientePieza = new VentanaNext(this, ventana, p);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(400,400);
        siguientePieza.setLayoutParams(params1);
        params1.topMargin=50;
        RelativeLayout relativeNext = (RelativeLayout) findViewById(R.id.ventanaSig);
        relativeNext.setBackgroundColor(findViewById(R.id.layoutprincipal).getSolidColor());
        relativeNext.setHorizontalGravity(1);
        relativeNext.addView(siguientePieza);

        Juego juego = new Juego(this, tablero, siguientePieza,modo);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        juego.setLayoutParams(params);
        RelativeLayout relativeTetris = (RelativeLayout) findViewById(R.id.layoutTablero);
        juego.setBackgroundColor(Color.LTGRAY);
        relativeTetris.addView(juego);

        menu = (Button)findViewById(R.id.settings);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);

            }
        }
        );
    }

    public ImageButton getBotonDcha() {
        return this.botonDcha;
    }

    public ImageButton getBotonBajar() {
        return this.botonBajar;
    }

    public ImageButton getBotonIzda() {
        return this.botonIzda;
    }

    public ImageButton getBotonRotar() {
        return this.botonRotar;
    }

    public TextView getPuntos() {
        return puntosTextView;
    }

    public TextView getNivel() {
        return nivelTextView;
    }

    public void gameOver(int p,int m){
        Intent intentGameOver = new Intent(this,Exit.class);
        intentGameOver.putExtra("puntuacionFinal",p);
        intentGameOver.putExtra("Modo",m);
        startActivity(intentGameOver);
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Tablero getVentana() {
        return ventana;
    }

    public void setBotonDcha(ImageButton botonDcha) {
        this.botonDcha = botonDcha;
    }

    public void setBotonBajar(ImageButton botonBajar) {
        this.botonBajar = botonBajar;
    }

    public void setBotonIzda(ImageButton botonIzda) {
        this.botonIzda = botonIzda;
    }

    public void setBotonRotar(ImageButton botonRotar) {
        this.botonRotar = botonRotar;
    }

    public void setPuntosTextView(TextView puntosTextView) {
        this.puntosTextView = puntosTextView;
    }

    public void setNivelTextView(TextView nivelTextView) {
        this.nivelTextView = nivelTextView;
    }
}
