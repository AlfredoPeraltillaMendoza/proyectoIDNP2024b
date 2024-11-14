package com.example.proyectoidnp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.HashMap;
import java.util.Map;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos el BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Llenamos el mapa con los IDs de los items del menú y sus correspondientes fragmentos
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.nav_home, new HomeFragment());
        fragmentMap.put(R.id.nav_info, new InfoFragment());
        fragmentMap.put(R.id.nav_profile, new ProfileFragment());
        fragmentMap.put(R.id.nav_map, new MapFragment()); // Añade MapFragment aquí

        // Cargar el fragmento inicial
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    // Listener del menú de navegación
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = fragmentMap.get(item.getItemId());

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();
                        return true;
                    }
                    return false;
                }
            };
}

