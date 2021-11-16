package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import Objetos.Administrador;
import Objetos.Insumos;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private TextView msj;
    private Button btn;
    private ProgressBar pb;
    private Administrador adm = new Administrador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.etuser);
        pass = findViewById(R.id.etpass);
        msj = findViewById(R.id.msj);
        btn= findViewById(R.id.btn);
        pb= findViewById(R.id.pb);

        msj.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.INVISIBLE); // DESAPARECE LA BARRA DE CARGA CIRCULAR


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    // FUNCION DE MI BUTTON

                new Task().execute(); // EJECUTA CLASE ASINCRONA

            }
        });
    }

    // TAREA ASINCRONA
    class Task extends AsyncTask<String, Void, String>
    {
        // DEFINE LA CONFIG INICIAL DE MI TAREA.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            msj.setText("--------");
            pb.setVisibility(View.VISIBLE);
        }

        // REALIZA PROCESO EN SEGUNDO PLANO PARA CORRER TAREA PESADA.
        @Override
        protected String doInBackground(String... strings) {
            try {
                for (int i = 0; i <= 10; i++)
                {
                    Thread.sleep(200);
                }
            }catch(InterruptedException e)
                {
                    e.printStackTrace();

                }

            return null;

        }

        // FINALIZA TAREA.
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            String usuario = user.getText().toString().trim();
            String contrasena = pass.getText().toString().trim();

            String userObj = adm.getUser().trim();
            String passObj = adm.getPass().trim();

            switch (usuario)
            {
                case "Matias":
                    if (usuario.equals(userObj) && contrasena.equals(passObj))
                    {

                        //INICIO SESION
                        msj.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(getBaseContext(), Home_act.class);


                        startActivity(i);
                    }
                    break;
                case "":
                    if (usuario.equals("") && contrasena.equals(""))
                    {
                        msj.setVisibility(View.VISIBLE);
                        msj.setText("Campos vacíos, por favor intenta nuevamente");
                    }
                    break;
                default:
                    if (!usuario.equals(userObj) && !contrasena.equals(passObj))
                    {
                        msj.setVisibility(View.VISIBLE);
                        msj.setText("Campos incorrectos por favor intentar nuevamente");

                    }
                    break;
            }
        }
    }

    public void Info(View view)
    {
        Intent i = new Intent(this, Info_act.class);
        startActivity(i);

    }

    public void Facebook(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW); // ABRE SITIO WEB.
        i.setData(Uri.parse("https://www.facebook.com/")); // RECIBE LA DIRECCION WEB
        startActivity(i); // INICIA EL ACTIVITY
    }

    public void Youtube(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW); // ABRE SITIO WEB.
        i.setData(Uri.parse("https://www.youtube.com/")); // RECIBE LA DIRECCION WEB
        startActivity(i); // INICIA EL ACTIVITY
    }

    public void Twitter(View view)
    {
        Intent i = new Intent(Intent.ACTION_VIEW); // ABRE SITIO WEB.
        i.setData(Uri.parse("https://www.twitter.com/")); // RECIBE LA DIRECCION WEB
        startActivity(i); // INICIA EL ACTIVITY
    }

}