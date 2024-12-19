package com.example.proyectoidnp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private ImagePagerAdapter adapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    private int currentPage = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Configurar ViewPager con imágenes
        viewPager = view.findViewById(R.id.viewpager_carrusel);
        List<Integer> imageList = Arrays.asList(
                R.drawable.santa_catalina,
                R.drawable.catedral,
                R.drawable.yanahuara,
                R.drawable.molino_sabandia,
                R.drawable.misti,
                R.drawable.la_compania,
                R.drawable.puente_fierro
        );

        adapter = new ImagePagerAdapter(getContext(), imageList);
        viewPager.setAdapter(adapter);

        // Iniciar el deslizamiento automático
        startAutoSlide();

        // Configurar botones de reproducción
        Button btnPlay = view.findViewById(R.id.btn_play);
        Button btnPause = view.findViewById(R.id.btn_pause);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MusicService.class);
            intent.setAction("PLAY");
            getActivity().startService(intent);
        });

        btnPause.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MusicService.class);
            intent.setAction("PAUSE");
            getActivity().startService(intent);
        });

        return view;
    }

    private void startAutoSlide() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentPage == adapter.getCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000); // Cambia cada 3 segundos
            }
        }, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null); // Detiene el deslizamiento al salir del fragmento
    }
}

