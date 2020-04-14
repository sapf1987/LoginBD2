package com.example.loginbd2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuSelecion extends AppCompatActivity {

    private Button btn_nuevaconsulta,btn_cancelarConsulta,btn_historialConsulta,btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_selecion);

        btn_nuevaconsulta = (Button) findViewById(R.id.btn_realizarNuevaConsulta);
        btn_cancelarConsulta = (Button) findViewById(R.id.btn_cancelarConsulta);
        btn_historialConsulta = (Button) findViewById(R.id.btn_historial);

        btn_nuevaconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irANuevaConsulta();
            }
        });

        btn_cancelarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irACancelarConsulta();
            }
        });

        btn_historialConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAHistorial();
            }
        });

        btn_salir = (Button) findViewById(R.id.btn_salir_inicio);

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornarInicio();
            }
        });
    }

    private void irANuevaConsulta(){
        startActivity(new Intent(MenuSelecion.this,MarcarConsulta.class));
        finish();
    }

    private void irACancelarConsulta(){
        startActivity(new Intent(MenuSelecion.this,CancelarConsulta.class));
        finish();
    }

    private void irAHistorial(){
        startActivity(new Intent(MenuSelecion.this,HistorialConsultas.class));
        finish();
    }

    private void retornarInicio(){
        startActivity(new Intent(MenuSelecion.this,Login.class));
        finish();
    }
}
