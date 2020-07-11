package com.example.tetris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OpcionesActivity extends AppCompatActivity {
    private Spinner sCuadrado;
    private Spinner sZ;
    private Spinner sI;
    private Spinner sT;
    private Spinner sS;
    private Spinner sL;
    private Spinner sJ;
    private Button atras;
    private final static String[] COLORES= new String[]{"Pink", "Light Blue", "Dark Blue", "Green", "Orange", "Yellow", "Red"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        atras = (Button)findViewById(R.id.button_atras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcionesActivity.this,Inicio.class);
                startActivity(intent);
            }
        });
        
        sCuadrado = (Spinner) findViewById(R.id.spinnerCuadrado);
        sZ = (Spinner) findViewById(R.id.spinnerZPieza);
        sI = (Spinner) findViewById(R.id.spinnerIPieza);
        sT = (Spinner) findViewById(R.id.spinnerTPieza);
        sS = (Spinner) findViewById(R.id.spinnerSPieza);
        sL = (Spinner) findViewById(R.id.spinnerLPieza);
        sJ = (Spinner) findViewById(R.id.spinnerJPieza);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.COLORES);

        sCuadrado.setAdapter(adapter);
        sZ.setAdapter(adapter);
        sI.setAdapter(adapter);
        sT.setAdapter(adapter);
        sS.setAdapter(adapter);
        sL.setAdapter(adapter);
        sJ.setAdapter(adapter);
    }

    public void setColor(View view) {
        String seleccionC = sCuadrado.getSelectedItem().toString();
        int colorIndex = indexItemArray(this.COLORES, seleccionC);
        if (colorIndex != -1){
            Tablero.setColorCuadrado(colorIndex+1);
        }

        String seleccionZ = sZ.getSelectedItem().toString();
        colorIndex = indexItemArray(this.COLORES, seleccionZ);
        if (colorIndex != -1){
            Tablero.setColorZPieza(colorIndex+1);
        }

        String seleccionI = sI.getSelectedItem().toString();
        colorIndex = indexItemArray(this.COLORES, seleccionI);
        if (colorIndex != -1){
            Tablero.setColorIPieza(colorIndex+1);
        }

        String seleccionT = sT.getSelectedItem().toString();
        colorIndex = indexItemArray(this.COLORES, seleccionT);
        if (colorIndex != -1){
            Tablero.setColorTPieza(colorIndex+1);
        }

        String seleccionS = sS.getSelectedItem().toString();
        colorIndex = indexItemArray(this.COLORES, seleccionS);
        if (colorIndex != -1){
            Tablero.setColorSPieza(colorIndex+1);
        }

        String seleccionL = sL.getSelectedItem().toString();
        colorIndex = indexItemArray(this.COLORES, seleccionL);
        if (colorIndex != -1){
            Tablero.setColorLPieza(colorIndex+1);
        }

        String seleccionJ = sJ.getSelectedItem().toString();
        colorIndex = indexItemArray(this.COLORES, seleccionJ);
        if (colorIndex != -1){
            Tablero.setColorJPieza(colorIndex+1);
        }

    }
    public static int indexItemArray(String[] array, String item){
        int index = -1;
        for (int i = 0; i<array.length; i++){
            if (array[i].equals(item)){
                index = i;
            }
        }
        return index;
    }


}
