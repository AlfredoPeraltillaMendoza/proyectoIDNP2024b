package com.example.proyectoidnp;

import android.database.Cursor;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView recyclerComentarios;

    private DatabaseHelper databaseHelper;
    private ComentarioAdapter comentarioAdapter;
    private List<Comentario> comentarios;

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
        recyclerComentarios = view.findViewById(R.id.recycler_comentarios);

        // Configurar RecyclerView
        recyclerComentarios.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista de comentarios
        comentarios = new ArrayList<>();
        comentarioAdapter = new ComentarioAdapter(getContext(), comentarios);
        recyclerComentarios.setAdapter(comentarioAdapter);

        // Inicializar base de datos
        databaseHelper = new DatabaseHelper(getContext());

        // Obtener datos de los argumentos
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_NOMBRE);
            descripcion = getArguments().getString(ARG_DESCRIPCION);
            imagenes = getArguments().getIntegerArrayList(ARG_IMAGENES);

            tvNombre.setText(nombre);
            tvDescripcion.setText(descripcion);
        }

        // Configurar ViewPager
        if (imagenes != null && !imagenes.isEmpty()) {
            ImagePagerAdapter adapter = new ImagePagerAdapter(getContext(), imagenes);
            viewPager.setAdapter(adapter);
        } else {
            Toast.makeText(getContext(), "No hay imágenes disponibles", Toast.LENGTH_SHORT).show();
        }

        // Cargar comentarios existentes
        loadComentarios();

        // Configurar botón para enviar comentario
        btnEnviarComentario.setOnClickListener(v -> {
            String comentarioTexto = etComentario.getText().toString().trim();
            float ratingValue = ratingBar.getRating();

            if (comentarioTexto.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, escribe un comentario", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ratingValue == 0) {
                Toast.makeText(getContext(), "Por favor, selecciona una calificación", Toast.LENGTH_SHORT).show();
                return;
            }

            // Obtener el último nombre de usuario registrado
            String userName = databaseHelper.getLastUserName();

            // Guardar comentario en la base de datos
            boolean isSaved = databaseHelper.saveComentario(nombre, userName, comentarioTexto, ratingValue);
            if (isSaved) {
                Toast.makeText(getContext(), "Comentario guardado", Toast.LENGTH_SHORT).show();
                Comentario nuevoComentario = new Comentario(userName, comentarioTexto, ratingValue);
                comentarios.add(nuevoComentario);
                comentarioAdapter.notifyDataSetChanged();

                // Limpiar campos
                etComentario.setText("");
                ratingBar.setRating(0);
            } else {
                Toast.makeText(getContext(), "Error al guardar el comentario", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void loadComentarios() {
        Cursor cursor = databaseHelper.getComentarios(nombre);
        while (cursor.moveToNext()) {
            String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME));
            String comentarioTexto = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMENTARIO));
            float ratingValue = cursor.getFloat(cursor.getColumnIndex(DatabaseHelper.COLUMN_RATING));
            Comentario comentario = new Comentario(userName, comentarioTexto, ratingValue);
            comentarios.add(comentario);
        }
        cursor.close();
        comentarioAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}

