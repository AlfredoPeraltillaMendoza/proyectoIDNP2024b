package com.example.proyectoidnp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Arrays;
import java.util.List;

public class InfoFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnShowMap;  // Botón para ver el plano interno de la catedral

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        // Inicializar el RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configurar la lista de edificaciones
        List<AtractivoTuristico> atractivosList = Arrays.asList(
                new AtractivoTuristico("Monasterio de Santa Catalina", "Convento de la época colonial", R.drawable.santa_catalina),
                new AtractivoTuristico("Catedral de Arequipa", "Ubicada en la Plaza de Armas", R.drawable.catedral),
                new AtractivoTuristico("Yanahuara", "Mirador con vistas a la ciudad", R.drawable.yanahuara),
                new AtractivoTuristico("Molino de Sabandía", "Antiguo molino del siglo XVII", R.drawable.molino_sabandia),
                new AtractivoTuristico("Museo Santuarios Andinos", "Hogar de la momia Juanita", R.drawable.museo_santuarios),
                new AtractivoTuristico("Plaza de Armas", "Plaza principal rodeada de arquitectura colonial", R.drawable.plaza_armas),
                new AtractivoTuristico("Misti", "El famoso volcán de Arequipa", R.drawable.misti),
                new AtractivoTuristico("Puente de Fierro", "Diseñado por Gustavo Eiffel", R.drawable.puente_fierro),
                new AtractivoTuristico("La Compañía", "Iglesia jesuita con fachada barroca", R.drawable.la_compania)
        );

        // Pasar el contexto y la lista de atractivos al adaptador
        EdificacionesAdapter adapter = new EdificacionesAdapter(getContext(), atractivosList);
        recyclerView.setAdapter(adapter);

        // Configurar el botón para ver el plano de la catedral
        btnShowMap = view.findViewById(R.id.btn_show_map);
        btnShowMap.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new CathedralMapFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }
}



