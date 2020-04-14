package com.example.loginbd2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginbd2.models.Lista_cancelar;

import java.util.ArrayList;

public class CancelarConsulta extends AppCompatActivity {

    private Button btn_volverMenu;
    private ListView lista_cancelar;
    private ArrayList<Lista_cancelar> listadocancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_consulta);

        lista_cancelar = (ListView) findViewById(R.id.lista_cancelar);

        listadocancelar = new ArrayList<>();

        //// LLamamos a la base de datos y consultamos los valores posibles a cancelar
        //consultarBdcancelar();

        //LLAMAMOS A LA BASE DE DATOS CREADA EN NUESTRA CLASE
        AdminBDSQLite administrar = new AdminBDSQLite(this, "BaseDatosAdministracion", null, 1);
        //LLAMAMOS A ESCRIBIRLA MODIFICARLA ETC
        SQLiteDatabase db = administrar.getWritableDatabase();

        Cursor mostrarConsultas = db.rawQuery("select id,especialidad,doctores,dias,estado from consultas where estado = 'Activo'", null);
        if (mostrarConsultas.moveToFirst()){
            do {
                int id = mostrarConsultas.getInt(mostrarConsultas.getColumnIndex("id"));
                String especialidad = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("especialidad"));
                String doctores = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("doctores"));
                String dias = mostrarConsultas.getString(mostrarConsultas.getColumnIndex("dias"));

                Lista_cancelar lista = new Lista_cancelar(id,especialidad,doctores,dias);
                listadocancelar.add(lista);

            }while (mostrarConsultas.moveToNext());
        }else{
            Toast.makeText(this,"No tiene consultas realizadas.", Toast.LENGTH_SHORT).show();
        }
        db.close();

        final MyAdaptercancelar myadapter = new MyAdaptercancelar(listadocancelar,R.layout.entrada_cancelar, this);
        lista_cancelar.setAdapter(myadapter);

        //////////////////////////////////////////////////////////////////////

        // ESTA FUNCION ES PARA MANTENER PRECIONADO Y QUE SALGA UN AVISO SI DECEA ELIMANR LA CONSULTA
        lista_cancelar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                final int posicion = i;

                final Lista_cancelar itemid = listadocancelar.get(i); // creamos una variable del tipo Lista_cancelar para obtener el id a cancelar

                // Mostramos los dialogos de advertencia
                AlertDialog.Builder advertencia = new AlertDialog.Builder(CancelarConsulta.this);
                advertencia.setTitle("Confirmar.");
                advertencia.setMessage("¿ Seguro desea eliminar esta consulta ?");
                advertencia.setCancelable(false);
                advertencia.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface advertencia, int id) {

                        listadocancelar.remove(posicion);
                        lista_cancelar.invalidateViews();
                        myadapter.notifyDataSetChanged();
                        eliminardeBd(itemid.getId_c()); // obtenemos el id del item selecionado con itemid.getId_c

                    }
                });
                advertencia.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface advertencia, int id) {

                    }
                });
                advertencia.show();
                return false;
            }
        });

        ///////////////////////////////////////////////////////////////////

        btn_volverMenu = (Button) findViewById(R.id.btn_volverMenuC);
        btn_volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAMenu_de_C();
            }
        });
    }

    private void eliminardeBd(int i){
        //LLAMAMOS A LA BASE DE DATOS CREADA EN NUESTRA CLASE
        AdminBDSQLite administrar = new AdminBDSQLite(this, "BaseDatosAdministracion", null, 1);
        //LLAMAMOS A ESCRIBIRLA MODIFICARLA ETC
        SQLiteDatabase db = administrar.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("estado","Cancelado");

        // Para eliminar,tenemos que actualizar el estado de activo a cancelado
        int eliminadoConfirmado = db.update("consultas", registro, "id="+i, null);
        db.close();

        if (eliminadoConfirmado == 1){
            Toast.makeText(this, "Se ah borrado la consulta correctamente.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No existe o no se pudo borrar corectamente la consulta, intente nuevamente.", Toast.LENGTH_SHORT).show();
        }

    }

    private void volverAMenu_de_C(){
        startActivity(new Intent(CancelarConsulta.this,MenuSelecion.class));
        finish();
    }
}
