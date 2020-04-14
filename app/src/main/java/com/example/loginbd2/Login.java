package com.example.loginbd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText us_cedula,us_pass;
    private Button crear_cuenta,btn_iniciar_seccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        us_cedula = (EditText) findViewById(R.id.edt_cedula);
        us_pass = (EditText) findViewById(R.id.edt_pass);

        crear_cuenta = (Button) findViewById(R.id.btn_crear_cuenta);
        btn_iniciar_seccion = (Button) findViewById(R.id.btn_iniciar_seccion);

        crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irACrearCuenta();
            }
        });

        btn_iniciar_seccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarSecccion();
            }
        });
    }

    private void IniciarSecccion(){
        // LLAMAMOS A LA BASE DE DATOS
        AdminBDSQLite administrar = new AdminBDSQLite(this, "BaseDatosAdministracion",null,1);
        SQLiteDatabase db = administrar.getWritableDatabase();

        String cedula = us_cedula.getText().toString();
        String password = us_pass.getText().toString();


        //vamos hacer la peticion, con cursor es una posicion de memoria un apuntador
        Cursor entrada = db.rawQuery("select cedula,password from usuarios where cedula ='"+cedula+"'and password ='"+password+"'",null);
        if (entrada.moveToFirst()){
            String con_cedula = entrada.getString(0);
            String con_password = entrada.getString(1);
            if(cedula.equals(con_cedula) && password.equals(con_password)) {
                startActivity(new Intent(Login.this,MenuSelecion.class));
                finish();
                Toast.makeText(this, "Usuario Ingresado Correctamente.", Toast.LENGTH_SHORT).show();
                //Aqui codigo
                us_cedula.setText("");
                us_pass.setText("");
            }
        }else{
            Toast.makeText(this, "Cedula o contraseña Incorrectos.", Toast.LENGTH_SHORT).show();
            //Aqui codigo
        }

    }

    private void irACrearCuenta(){
        startActivity(new Intent(Login.this,Registrar.class));
        finish();
    }


}
