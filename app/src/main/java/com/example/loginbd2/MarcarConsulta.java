package com.example.loginbd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MarcarConsulta extends AppCompatActivity {

    private Button btn_volverMenu,btn_guardarConsulta;
    private Spinner spin_especialidades,spin_doctores,spin_dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_consulta);

        btn_volverMenu = (Button) findViewById(R.id.btn_volverMenuM);
        btn_volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMenu_de_M();
            }
        });

        btn_guardarConsulta = (Button) findViewById(R.id.btn_guard_Consulta);
        btn_guardarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun_guard_NConsulta();
            }
        });

        spin_especialidades = (Spinner) findViewById(R.id.Spin_Especialidades);
        spin_doctores = (Spinner) findViewById(R.id.Spin_Doctor);
        spin_dias = (Spinner) findViewById(R.id.Spin_Dias);

        ArrayAdapter<CharSequence> adapterEspecialidades = ArrayAdapter.createFromResource(this,R.array.Especialidad,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterDoctores = ArrayAdapter.createFromResource(this,R.array.Doctores,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterDias = ArrayAdapter.createFromResource(this,R.array.Dias,android.R.layout.simple_spinner_item);

        spin_especialidades.setAdapter(adapterEspecialidades);
        spin_doctores.setAdapter(adapterDoctores);
        spin_dias.setAdapter(adapterDias);

        spin_especialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_doctores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_dias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void volverAMenu_de_M(){
        startActivity(new Intent(MarcarConsulta.this,MenuSelecion.class));
        finish();
    }

    private void fun_guard_NConsulta(){
        //LLAMAMOS A LA BASE DE DATOS CREADA EN NUESTRA CLASE
        AdminBDSQLite administrar = new AdminBDSQLite(this, "BaseDatosAdministracion", null, 1);
        //LLAMAMOS A ESCRIBIRLA MODIFICARLA ETC
        SQLiteDatabase db = administrar.getWritableDatabase();

        // hacemos un nuevo registro y su insercion
        ContentValues registro = new ContentValues();

        String especialidad = (String) spin_especialidades.getSelectedItem();
        String doctor = (String) spin_doctores.getSelectedItem();
        String dia = (String) spin_dias.getSelectedItem();

        registro.put("especialidad",especialidad);
        registro.put("doctores",doctor);
        registro.put("dias",dia);
        registro.put("estado","Activo"); // Siempre que se marca una nueva consulta esta activa.

        db.insert("consultas", null, registro);
        db.close();

        // pones las entradas de vuelta en vacio
        // Falta codigo

        Toast.makeText(this,"Nueva consulta exitosa.",Toast.LENGTH_SHORT).show();
    }

}
