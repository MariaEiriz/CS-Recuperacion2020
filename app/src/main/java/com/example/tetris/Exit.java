package com.example.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Exit extends AppCompatActivity {
    private EditText et_nombre;
    //private Button registrar;
    private TextView textoRanking1,textoRanking2,textoRanking3,textoRanking4,textoRanking5,textoRanking6,mostrarPunt1,mostrarPunt2,mostrarPunt3,mostrarPunt4,mostrarPunt5,mostrarPunt6,textPuntActual;
    private SQLiteDatabase BaseDeDatos;
    private AdminSQLiteOpenHelper BBDD;
    private boolean registrado;
    private int puntosFinal;
    private int modo;
    private String tipoBBDD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle p = this.getIntent().getExtras();
        puntosFinal = p.getInt("puntuacionFinal");
        modo = p.getInt("Modo");

        BBDD = new AdminSQLiteOpenHelper(this, "RankingJugadores", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        Button again = findViewById(R.id.Again);
        Button registrar = findViewById(R.id.registrar_puntuacion);
        Button restEstadisticas =  findViewById(R.id.restablecerEstadisticas);

        //TextView nombre = findViewById(R.id.nombre_jugador);
        registrado=false;

        textoRanking1 = findViewById(R.id.mostrarRanking1);
        textoRanking2 = findViewById(R.id.mostrarRanking2);
        textoRanking3 = findViewById(R.id.mostrarRanking3);
        textoRanking4 = findViewById(R.id.mostrarRanking4);
        textoRanking5 = findViewById(R.id.mostrarRanking5);
        textoRanking6 = findViewById(R.id.mostrarRanking6);

        mostrarPunt1= findViewById(R.id.textpunt1);
        mostrarPunt2= findViewById(R.id.textpunt2);
        mostrarPunt3= findViewById(R.id.textpunt3);
        mostrarPunt4= findViewById(R.id.textpunt4);
        mostrarPunt5= findViewById(R.id.textpunt5);
        mostrarPunt6= findViewById(R.id.textpunt6);

        et_nombre = (EditText)findViewById(R.id.nombre_jugador);
        textPuntActual= findViewById(R.id.text_puntuacionActual);

        mostrarTop5();

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Exit.this, Inicio.class);
                startActivity(intent);
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //restablecerEstadiaticas();

                /*
                mostrarRanking1.setVisibility(view.VISIBLE);
                mostrarRanking2.setVisibility(view.VISIBLE);
                mostrarRanking3.setVisibility(view.VISIBLE);
                mostrarRanking4.setVisibility(view.VISIBLE);
                mostrarRanking5.setVisibility(view.VISIBLE);
                mostrarRanking6.setVisibility(view.VISIBLE);
                */

                Registrar(view);
                mostrarTop5();
            }
        });

        restEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restablecerEstadiaticas(v);
            }
        });
    }
    public void Registrar(View view){
        //abrir la base de datos modo escritura y lectura
        BaseDeDatos = BBDD.getWritableDatabase();
        et_nombre = (EditText)findViewById(R.id.nombre_jugador);
        String nombre = et_nombre.getText().toString();


        if (!nombre.isEmpty() & !registrado){
            //permite almacenar las columnas del registro en pares clave-valor
            ContentValues registro = new ContentValues();
            //Añade los pares
            registro.put("nombre", nombre);
            registro.put("puntuacion", puntosFinal);

            //insertar valores en la tabla ranking
            if(modo==0){
                BaseDeDatos.insert("rankingNormal", null, registro);
            }else{
                BaseDeDatos.insert("rankingHard", null, registro);
            }
            BaseDeDatos.close();

            et_nombre.setText("");
            registrado=true;


        }else{
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Debes rellenar el nombre", Toast.LENGTH_SHORT).show();
            }
            if (registrado){
                Toast.makeText(this, "Ya te has registrado", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void mostrarTop5 (){
        BaseDeDatos = BBDD.getWritableDatabase();
        String columnas[] = new String[]{"nombre","puntuacion"};//,"puntuacion"
        textPuntActual.setText(Integer.toString(puntosFinal));
        String j1="",j2="",j3="",j4="",j5="",j6="",p1="",p2="",p3="",p4="",p5="",p6="";

        //********************** AMBAS FUNCIONAN
        //-----1 forma
        if(modo==0){
            tipoBBDD = "rankingNormal";
        }else{
            tipoBBDD = "rankingHard";
        }
            Cursor fila1 =BaseDeDatos.rawQuery("select * from "+tipoBBDD+"  order by puntuacion DESC",null);

        //-----2 forma
            //Cursor fila2 = BaseDeDatos.query("rankingNormal", columnas, null, null, null, null, "puntuacion"+" DESC");
        if(fila1.moveToFirst()){
            //mostrarRanking.setText(fila1.getString(0)+fila1.getString(1));

            j1 = fila1.getString(0);
            p1 = fila1.getString(1);

            if (fila1.moveToNext()) {
                j2 = fila1.getString(0);
                p2 = fila1.getString(1);
            }
            if (fila1.moveToNext()) {
                j3 = fila1.getString(0);
                p3 = fila1.getString(1);
            }
            if (fila1.moveToNext()) {
                j4 = fila1.getString(0);
                p4 = fila1.getString(1);
            }
            if (fila1.moveToNext()) {
                j5 = fila1.getString(0);
                p5 = fila1.getString(1);
            }
            if (fila1.moveToNext()) {
                j6 = fila1.getString(0);
                p6= fila1.getString(1);
            }


        }else {
            
            j1="";
            j2="";
            j3="";
            j4="";
            j5="";
            j6="";
            p1="";
            p2="";
            p3="";
            p4="";
            p5="";
            p6="";
        }

        textoRanking1.setText(j1);
        textoRanking2.setText(j2);
        textoRanking3.setText(j3);
        textoRanking4.setText(j4);
        textoRanking5.setText(j5);
        textoRanking6.setText(j6);

        mostrarPunt1.setText(p1);
        mostrarPunt2.setText(p2);
        mostrarPunt3.setText(p3);
        mostrarPunt4.setText(p4);
        mostrarPunt5.setText(p5);
        mostrarPunt6.setText(p6);

        BaseDeDatos.close();
    }

    public void restablecerEstadiaticas (View view){
        BaseDeDatos = BBDD.getWritableDatabase();

        if(modo==0){
            tipoBBDD = "rankingNormal";
        }else{
            tipoBBDD = "rankingHard";
        }
        BaseDeDatos.execSQL("DELETE FROM "+tipoBBDD);
        /*
        Cursor fila1 =BaseDeDatos.rawQuery("select * from rankingNormal",null);

        if (fila1 != null) {
            fila1.moveToFirst();
            do{
                BaseDeDatos.execSQL("DELETE FROM rankingNormal");

            }while (fila1.moveToNext())
        }
        fila1.close();
        */
        Toast.makeText(this, "Estadisticas restablecidas", Toast.LENGTH_SHORT).show();
        BaseDeDatos.close();
        mostrarTop5();
    }
}