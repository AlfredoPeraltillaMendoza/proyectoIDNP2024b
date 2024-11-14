package com.example.proyectoidnp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.osmdroid.views.overlay.Marker;

public class MapFragment extends Fragment {

    private MapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(requireContext(), getActivity().getPreferences(Context.MODE_PRIVATE));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = view.findViewById(R.id.map_view);
        mapView.setMultiTouchControls(true);

        // Centra el mapa en una ubicación específica con un zoom inicial adecuado
        mapView.getController().setCenter(new GeoPoint(-16.409047, -71.537451)); // Ejemplo de Arequipa
        mapView.getController().setZoom(15.0); // Ajusta el nivel de zoom

        setupTouristAttractions();

        return view;
    }


    private void setupTouristAttractions() {
        // Coordenadas de los atractivos turísticos
        double[][] touristAttractions = {
                { -16.398803, -71.536928 }, // Monasterio de Santa Catalina
                { -16.398761, -71.537451 }, // Catedral de Arequipa
                { -16.394802, -71.536939 }, // Yanahuara
                { -16.457389, -71.492380 }, // Molino de Sabandía
                { -16.398989, -71.537369 }, // Museo Santuarios Andinos
                { -16.398766, -71.536921 }, // Plaza de Armas
                { -16.295893, -71.405176 }, // Misti
                { -16.403656, -71.534867 }, // Puente de Fierro
                { -16.398791, -71.537500 }  // La Compañía
        };

        for (double[] location : touristAttractions) {
            Marker marker = new Marker(mapView);
            marker.setPosition(new GeoPoint(location[0], location[1]));
            mapView.getOverlays().add(marker); // Añade el marcador al mapa
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume(); // Necesario para el mapa
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause(); // Necesario para el mapa
    }
}



