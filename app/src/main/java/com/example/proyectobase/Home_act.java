package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import Objetos.Insumos;

public class Home_act extends AppCompatActivity {

    private VideoView video;
    private Insumos in = new Insumos(); //insumos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        video = findViewById(R.id.vw);

        // Obtengo la ruta
        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta); // Parseo la ruta.
        video.setVideoURI(uri); // video view le paso mi video.

        MediaController media = new MediaController(this);
        video.setMediaController(media);

    }

    // UN HILO MUY PESADO || UN PROCESO NO CONTROLADO

    public void Task(View view)
    {
        try {

            for(int i = 0; i <= 10; i++)
            {
                Thread.sleep(2000);
                Toast.makeText(this,"Tarea Larga Procesada", Toast.LENGTH_SHORT).show();
            }

        }catch (InterruptedException e)
        {
            e.printStackTrace(); // Imprime el error desde el stack

        }
    }

    public void Insumos (View view)
    {

        Intent i = new Intent(this, Insumos_act.class);

        // PREPARO LOS EXTRAS
        Bundle bun = new Bundle(); // NECESARIO PARA ENVIAR ARREGLOS
        bun.putStringArray("insumos", in.getInsumos()); // RELLENO EL BUNDLE
        i.putExtras(bun); // ENVIO EL BUNDLE DESDE EL INTENT

        startActivity(i);

    }
}