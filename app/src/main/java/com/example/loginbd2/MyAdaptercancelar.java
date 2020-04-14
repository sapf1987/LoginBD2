package com.example.loginbd2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.loginbd2.models.Lista_cancelar;

import java.util.ArrayList;

public class MyAdaptercancelar extends BaseAdapter {
    private ArrayList<Lista_cancelar> entradas;
    private int R_layout_IdView;
    private Context contexto;

    public MyAdaptercancelar(ArrayList<Lista_cancelar> entradas, int r_layout_IdView, Context contexto) {
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
        Lista_cancelar consulta = entradas.get(position);

        if (view == null) {
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }

        // Referenciamos el elemento a modificar
        TextView eta_id = view.findViewById(R.id.id_eta_cancelar);
        TextView eta_especialidad = view.findViewById(R.id.text_cancel_especialista);
        TextView eta_doctor = view.findViewById(R.id.text_cancel_doctor);
        TextView eta_dia = view.findViewById(R.id.text_cancel_dia);


        // Obtener los datos de la lista
        String identificador = String.valueOf(consulta.getId_c());
        String especialidad = consulta.getEspecialidad_c();
        String doctores = consulta.getDoctores_c();
        String dias = consulta.getDias_c();

        // Y poner a los TextView los datos con setText
        eta_id.setText(identificador);
        eta_especialidad.setText(especialidad);
        eta_doctor.setText(doctores);
        eta_dia.setText(dias);

        return view;
    }
}