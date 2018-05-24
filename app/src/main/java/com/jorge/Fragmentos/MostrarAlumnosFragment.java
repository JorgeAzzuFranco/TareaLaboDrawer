package com.jorge.Fragmentos;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jorge.Adapter.AlumnosAdapter;
import com.jorge.Datos.Alumno;
import com.jorge.Entidades.DBHelper;
import com.jorge.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarAlumnosFragment extends Fragment {

    View mostrarAlumnos;
    RecyclerView rv;
    LinearLayoutManager llm;
    ArrayList<Alumno> alumno;
    AlumnosAdapter adapter;

    public MostrarAlumnosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mostrarAlumnos = inflater.inflate(R.layout.fragment_mostrar_alumnos, container, false);

        rv = mostrarAlumnos.findViewById(R.id.rv);
        alumno = DBHelper.myDB.getAlumnos();

        adapter = new AlumnosAdapter(alumno,getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        return mostrarAlumnos;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
