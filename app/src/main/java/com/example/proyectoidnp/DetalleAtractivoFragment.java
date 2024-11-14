// DetalleAtractivoFragment.java
package com.example.proyectoidnp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;

public class DetalleAtractivoFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_DESCRIPCION = "descripcion";
    private static final String ARG_IMAGENES = "imagenes";

    private String nombre;
    private String descripcion;
    private ArrayList<Integer> imagenes;

    private TextView tvNombre, tvDescripcion;
    private RatingBar ratingBar;
    private EditText etComentario;
    private Button btnEnviarComentario;
    private ViewPager viewPager;

    public static DetalleAtractivoFragment newInstance(String nombre, String descripcion, ArrayList<Integer> imagenes) {
        DetalleAtractivoFragment fragment = new DetalleAtractivoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_DESCRIPCION, descripcion);
        args.putIntegerArrayList(ARG_IMAGENES, imagenes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_atractivo, container, false);

        // Inicializar vistas
        tvNombre = view.findViewById(R.id.tv_nombre);
        tvDescripcion = view.findViewById(R.id.tv_descripcion);
        ratingBar = view.findViewById(R.id.rating_bar);
        etComentario = view.findViewById(R.id.et_comentario);
        btnEnviarComentario = view.findViewById(R.id.btn_enviar_comentario);
        viewPager = view.findViewById(R.id.viewpager_imagenes);

        // Obtener los argumentos pasados
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            descripcion = getArguments().getString(ARG_DESCRIPCION);
            imagenes = getArguments().getIntegerArrayList(ARG_IMAGENES);

            tvNombre.setText(nombre);
            tvDescripcion.setText(descripcion);
        }

        // Configurar ViewPager con las imágenes
        ImagePagerAdapter adapter = new ImagePagerAdapter(getContext(), imagenes);
        viewPager.setAdapter(adapter);

        // Configurar botón de enviar comentario
        btnEnviarComentario.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String comentario = etComentario.getText().toString().trim();

            if (comentario.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, escribe un comentario", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getContext(), "Comentario enviado para " + nombre, Toast.LENGTH_SHORT).show();
            etComentario.setText("");
            ratingBar.setRating(0);
        });

        return view;
    }
}
