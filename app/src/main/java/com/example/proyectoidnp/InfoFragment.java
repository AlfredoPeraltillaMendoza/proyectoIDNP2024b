package com.example.proyectoidnp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {

    private RecyclerView recyclerView;
    private EdificacionesAdapter edificacionesAdapter;
    private List<String> edificacionesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Inicializar el RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Cargar la lista de edificaciones históricas
        cargarEdificaciones();

        // Configurar el adaptador
        edificacionesAdapter = new EdificacionesAdapter(edificacionesList);
        recyclerView.setAdapter(edificacionesAdapter);

        return view;
    }

    private void cargarEdificaciones() {
        edificacionesList = new ArrayList<>();
        edificacionesList.add("Monasterio de Santa Catalina");
        edificacionesList.add("Catedral de Arequipa");
        edificacionesList.add("Iglesia de la Compañía");
        edificacionesList.add("Casa del Moral");
        edificacionesList.add("Iglesia San Francisco");
        edificacionesList.add("Mansión del Fundador");
        edificacionesList.add("Molino de Sabandía");
        edificacionesList.add("Plaza de Armas de Arequipa");
        edificacionesList.add("Casa Goyeneche");
        edificacionesList.add("Puente de Fierro");
        // Agrega más edificaciones históricas de Arequipa aquí...
    }
}
