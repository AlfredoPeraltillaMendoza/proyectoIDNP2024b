package com.example.proyectoidnp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private TextView welcomeMessage;
    private ImageView image1, image2, image3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Configurar el mensaje de bienvenida
        welcomeMessage = view.findViewById(R.id.welcome_message);
        welcomeMessage.setText("¡Bienvenido a la aplicación de Edificaciones Históricas de Arequipa!");

        // Configurar imágenes simples
        image1 = view.findViewById(R.id.image1);
        image1.setImageResource(R.drawable.edificacion1);

        image2 = view.findViewById(R.id.image2);
        image2.setImageResource(R.drawable.edificacion2);

        image3 = view.findViewById(R.id.image3);
        image3.setImageResource(R.drawable.edificacion3);

        return view;
    }
}
