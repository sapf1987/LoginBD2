package com.example.loginbd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {

    private EditText ed_cedula,ed_nombre,ed_apellido,ed_telefono,ed_correo,ed_paswword;

    private Button btn_volver_login,btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        ed_cedula = (EditText) findViewById(R.id.reg_cedula);
        ed_nombre = (EditText) findViewById(R.id.reg_nombre);
        ed_apellido = (EditText) findViewById(R.id.reg_apellido);
        ed_telefono = (EditText) findViewById(R.id.reg_telefono);
        ed_correo = (EditText) findViewById(R.id.reg_correo);
        ed_paswword = (EditText) findViewById(R.id.reg_contraseña);

        //BOTON REGISTAR USUARIO CON SU RESPECTIVO LISTENER
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

        //BOTON VOLVER AL LOGIN
        btn_volver_login = (Button) findViewById(R.id.btn_ir_iniciar);
        btn_volver_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverALogin();
            }
        });

    }

    private void registrarUsuario(){
        //LLAMAMOS A LA BASE DE DATOS CREADA EN NUESTRA CLASE
        AdminBDSQLite administrar = new AdminBDSQLite(this, "BaseDatosAdministracion", null, 1);
        //LLAMAMOS A ESCRIBIRLA MODIFICARLA ETC
        SQLiteDatabase db = administrar.getWritableDatabase();

        String cedula = ed_cedula.getText().toString();
        String nombre = ed_nombre.getText().toString();
        String apellido = ed_apellido.getText().toString();
        String telefono = ed_telefono.getText().toString();
        String correo = ed_correo.getText().toString();
        String password = ed_paswword.getText().toString();

        // hacemos un nuevo registro y su insercion
        ContentValues registro = new ContentValues();
        registro.put("cedula",cedula);
        registro.put("nombre",nombre);
        registro.put("apellido",apellido);
        registro.put("telefono",telefono);
        registro.put("correo",correo);
        registro.put("password",password);

        db.insert("usuarios", null, registro);
        db.close();

        // pones las entradas de vuelta en vacio
        ed_cedula.setText("");
        ed_nombre.setText("");
        ed_apellido.setText("");
        ed_telefono.setText("");
        ed_correo.setText("");
        ed_paswword.setText("");

        Toast.makeText(this, "Usuario Registrado.", Toast.LENGTH_SHORT).show();

    }
    private void volverALogin(){
        startActivity(new Intent(Registrar.this,Login.class));
        finish();
    }
}
