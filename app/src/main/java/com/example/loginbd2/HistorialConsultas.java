package com.example.loginbd2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginbd2.models.Lista_consultas;

import java.util.ArrayList;

public class HistorialConsultas extends AppCompatActivity {

    private Button btn_volverMenu;
    private ListView lista_historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_consultas);

        ListView lista_consulta = findViewById(R.id.lista_consulta);

        ArrayList<Lista_consultas> listadoconsultas = new ArrayList<>();

        //LLAMAMOS A LA BASE DE DATOS CREADA EN NUESTRA CLASE
        AdminBDSQLite administrar = new AdminBDSQLite(this, "BaseDatosAdministracion", null, 1);
        //LLAMAMOS A ESCRIBIRLA MODIFICARLA ETC
        SQLiteDatabase db = administrar.getWritableDatabase();

        Cursor mostrarConsultas = db.rawQuery("select id,especialidad,doctores,dias,estado from consultas", null);
        if (mostrarConsultas.moveToFirst()){
            do {
                int id = mostrarConsultas.getInt(mostrarConsultas.getColumnIndex("id"));
                String especialidad = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("especialidad"));
                String doctores = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("doctores"));
                String dias = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("dias"));
                String estado = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("estado"));

                Lista_consultas lista = new Lista_consultas(id,especialidad,doctores,dias,estado);
                listadoconsultas.add(lista);

            }while (mostrarConsultas.moveToNext());
        }else{
            Toast.makeText(this,"No tiene consultas realizadas.", Toast.LENGTH_SHORT).show();
        }
        db.close();

        MyAdapterconsultas myadapter = new MyAdapterconsultas(listadoconsultas,R.layout.entrada_consulta, this);
        lista_consulta.setAdapter(myadapter);


        btn_volverMenu = (Button) findViewById(R.id.btn_volverMenuH);
        btn_volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMenu_de_H();
            }
        });
    }

    private void volverAMenu_de_H(){
        startActivity(new Intent(HistorialConsultas.this,MenuSelecion.class));
        finish();
    }
}
