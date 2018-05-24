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
public class AddAlumnoFragment extends Fragment {

    View addAlumno;
    EditText editCarnet, editNombre;
    Button btnGuardar;

    public AddAlumnoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DBHelper.getInstance(getContext());

        addAlumno = inflater.inflate(R.layout.fragment_add_alumno, container, false);
        iniciarComponentes(addAlumno);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCarnet.getText().toString().isEmpty() && editNombre.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Uno o ambos campos estan vacios", Toast.LENGTH_SHORT).show();
                }
                else {
                    String carnet = editCarnet.getText().toString();
                    String nombre = editNombre.getText().toString();
                    float nota = 0;

                    boolean flag = DBHelper.myDB.addAlumno(new Alumno(carnet, nombre, nota));

                    if (flag){
                        Toast.makeText(getContext(), "Alumno ingresado con exito", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }
                    else{
                        Toast.makeText(getContext(), "Error Alumno no ingresado", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return addAlumno;
    }

    public void iniciarComponentes(View v){
        editCarnet = v.findViewById(R.id.editCarnet);
        editNombre = v.findViewById(R.id.editNombre);
        btnGuardar = v.findViewById(R.id.btnGuardar);
    }

    private void limpiar() {
        editCarnet.setText("");
        editNombre.setText("");
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
