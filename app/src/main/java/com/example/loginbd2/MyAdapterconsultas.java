package com.example.loginbd2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.loginbd2.models.Lista_consultas;

import java.util.ArrayList;

public class MyAdapterconsultas extends BaseAdapter {

    private ArrayList<Lista_consultas> entradas;
    private int R_layout_IdView;
    private Context contexto;

    public MyAdapterconsultas(ArrayList<Lista_consultas> entradas, int r_layout_IdView, Context contexto) {
        this.entradas = entradas;
        R_layout_IdView = r_layout_IdView;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int position) {
        return entradas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Lista_consultas consulta = entradas.get(position);

        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }

        // Referenciamos el elemento a modificar
        TextView eta_id = view.findViewById(R.id.id_consulta);
        TextView eta_especialidad = view.findViewById(R.id.textView_superior_espe);
        TextView eta_doctor = view.findViewById(R.id.textView_inferior_izquierdo);
        TextView eta_dia = view.findViewById(R.id.textView_inferior_central);
        TextView eta_estado = view.findViewById(R.id.estado_consulta);


        // Obtener los datos de la lista
        String identificador = String.valueOf(consulta.getId());
        String especialidad = consulta.getEspecialidad();
        String doctores = consulta.getDoctores();
        String dias = consulta.getDias();
        String estado = consulta.getEstado();

        // Y poner a los TextView los datos con setText
        eta_id.setText(identificador);
        eta_especialidad.setText(especialidad);
        eta_doctor.setText(doctores);
        eta_dia.setText(dias);
        eta_estado.setText(estado);

        return view;
    }
}
