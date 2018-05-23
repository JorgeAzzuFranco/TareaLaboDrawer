package com.jorge.Fragmentos;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jorge.Datos.Alumno;
import com.jorge.Entidades.DBHelper;
import com.jorge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditarAlumnoFragment extends Fragment {

    View editAlumnos;
    EditText editCarnet, editNombre, editNota;
    Button guardar, actualizar, eliminar, buscar;

    public EditarAlumnoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DBHelper.getInstance(getContext());

        editAlumnos = inflater.inflate(R.layout.fragment_editar_alumno, container, false);

        iniciarComponentes(editAlumnos);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alumno a = DBHelper.myDB.buscarAlumno(editCarnet.getText().toString());

                if (a == null){
                    Toast.makeText(getContext(), "El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
                else{
                    editNota.setText(a.getNota()+"");
                    editNombre.setText(a.getNombre());
                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return editAlumnos;
    }

    private void limpiar() {
        editCarnet.setText("");
        editNota.setText("");
        editNombre.setText("");

    }

    public void iniciarComponentes(View v){
        editCarnet = v.findViewById(R.id.editCarnetMod);
        editNombre = v.findViewById(R.id.editNombreMod);
        editNota = v.findViewById(R.id.editNotaMod);
        guardar = v.findViewById(R.id.btnGuardarMod);
        actualizar = v.findViewById(R.id.btnActualizarMod);
        eliminar = v.findViewById(R.id.btnEliminarMod);
        buscar = v.findViewById(R.id.btnBuscar);

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
