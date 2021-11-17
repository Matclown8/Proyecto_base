package com.example.proyectobase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectobase.database.AdminSQLiteOpenHelper;

public class Clases_act extends AppCompatActivity {

    private EditText code, clase, intensi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);


        code = findViewById(R.id.code);
        clase = findViewById(R.id.clase);
        intensi = findViewById(R.id.intensi);
    }

    // Método para añadirClases

    public void guardarClase(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); // Me permite sobrreescribir la database

        String codigo = code.getText().toString();
        String clases = clase.getText().toString();
        String intensidad = intensi.getText().toString();

        if(!codigo.isEmpty() && !clases.isEmpty() && !intensidad.isEmpty())
        {
            // añadimos en la database
            ContentValues cont = new ContentValues(); // permrite añadir valores a mi database
            cont.put("codigo", codigo);
            cont.put("clases", clases);
            cont.put("intensidad", intensidad);
            db.insert("clases", null, cont); // se hace el insert
            db.close();
            Clean(); // limpia los campos
            Toast.makeText(this, "Has Guardado una clase", Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(this,"Los campos están vacíos", Toast.LENGTH_LONG).show();

            }
        }

    // Método para mostrar clase
    public void mostrarClase(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); // Me permite sobreescribir la database

        String cod = code.getText().toString();

        if(!cod.isEmpty())
        {

            Cursor file = db.rawQuery
                    ("SELECT clases, intensidad FROM clases WHERE codigo=" +cod, null);

            if(file.moveToFirst())
            {
                clase.setText(file.getString(0));
                intensi.setText(file.getString(1));


            }
        }
        else
        {
            Toast.makeText(this, "No hay clase asociada", Toast.LENGTH_LONG).show();

        }

    }

    // método para eliminar clase
    public void eliminarClase(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); // Me permite sobreescribir la database

        String codigo = code.getText().toString();

        if (!codigo.isEmpty())
        {
            int cant = db.delete("clases", "codigo=" +codigo, null);
            db.close();
            Clean();

            if (cant==1) {
                Toast.makeText(getBaseContext(), "Eliminaste una clase asociada a: " +codigo, Toast.LENGTH_SHORT).show();

            }else
            {

                Toast.makeText(getBaseContext(), "No existe la clase en la database", Toast.LENGTH_SHORT).show();

            }
        }else

        {
            Toast.makeText(getBaseContext(), "No hay clase asociada", Toast.LENGTH_SHORT).show();

        }

    }

    // Método para actualizazr clase
    public void actualizarClase(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "biofit", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase(); // Me permite sobreescribir la database

        String codigo = code.getText().toString();
        String clases = clase.getText().toString();
        String intensidad = intensi.getText().toString();

        if(!codigo.isEmpty() && !clases.isEmpty() && !intensidad.isEmpty())
        {
            ContentValues cont = new ContentValues();
            cont.put("clases", clases);
            cont.put("intensidad", intensidad);

            db.update("clases", cont, "codigo="+codigo,null);
            db.close();
            Clean();

            Toast.makeText(getBaseContext(),"Se ha actualizado correctamente", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(getBaseContext(), "Hay campos vacíos", Toast.LENGTH_SHORT).show();

        }
    }

    public void Clean()
    {

        code.setText("");
        clase.setText("");
        intensi.setText("");

    }
}