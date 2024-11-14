// ComentarioFragment.java
package com.example.proyectoidnp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ComentarioFragment extends Fragment {

    private RatingBar ratingBar;
    private EditText etComentario;
    private Button btnEnviarComentario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comentario, container, false);

        ratingBar = view.findViewById(R.id.rating_bar);
        etComentario = view.findViewById(R.id.et_comentario);
        btnEnviarComentario = view.findViewById(R.id.btn_enviar_comentario);

        btnEnviarComentario.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String comentario = etComentario.getText().toString().trim();

            if (comentario.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, escribe un comentario", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí puedes guardar el comentario y la calificación (en base de datos o lista)
            Toast.makeText(getContext(), "Comentario enviado", Toast.LENGTH_SHORT).show();
            etComentario.setText("");
            ratingBar.setRating(0);
        });

        return view;
    }
}


