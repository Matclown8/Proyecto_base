package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import Objetos.Insumos;

public class Insumos_act extends AppCompatActivity {

    private Spinner insumos;
    private TextView result;
    private RatingBar estrellas;
    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);

        insumos = findViewById(R.id.spninsumos);
        result = findViewById(R.id.result);
        estrellas = (RatingBar) findViewById(R.id.rtb);

        // RECIBO LOS EXTRAS
        Bundle bun = getIntent().getExtras(); // OBTENGO MI BUNDLE
        String[] listado = bun.getStringArray("insumos"); // GUARDA LA LISTA DESDE

        //RELLENO DE SPINNER

        ArrayAdapter adaptInsumos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listado);
        insumos.setAdapter(adaptInsumos);

    }

    // METODO CALCULO DE INSUMOS
    public void Calcular(View view)
    {

        String opcion = insumos.getSelectedItem().toString();
        int resultado = 0;
        for(int i = 0; i < opcion.length(); i++)
        {
            if(opcion.equals(in.getInsumos()[i])) // Prergunta insumo seleccionado
            {
                //resultado = in.getPrecios()[i];
                resultado = in.anadirrAdicional(in.getPrecios()[i], 1500);
                estrellas.setRating(i+1); // pinta estrellas
                break;
            }

        }

        result.setText("La opción es: " + opcion + "\nSu precio es: " + resultado);


    }

}

