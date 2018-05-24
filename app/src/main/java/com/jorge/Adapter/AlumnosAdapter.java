package com.jorge.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jorge.Datos.Alumno;
import com.jorge.R;

import java.util.ArrayList;

public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.ViewHolder> {

    private View view;
    private static Context context;
    ArrayList<Alumno> a;

    public AlumnosAdapter(ArrayList<Alumno> a, Context context) {
        this.a = a;
        this.context = context;
    }

    @NonNull
    @Override
    public AlumnosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.cardview_alumno,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosAdapter.ViewHolder holder, int position) {
        holder.carnet.setText(a.get(position).getCarnet());
        holder.nota.setText(a.get(position).getNota()+"");
        holder.nombre.setText(a.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return a.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView carnet, nombre, nota;

        public ViewHolder(final View itemView) {
            super(itemView);
            carnet = itemView.findViewById(R.id.txtcarn);
            nota = itemView.findViewById(R.id.txtnot);
            nombre = itemView.findViewById(R.id.txtnom);
        }
    }
}
