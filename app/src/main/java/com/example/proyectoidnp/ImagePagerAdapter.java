// ImagePagerAdapter.java
package com.example.proyectoidnp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private List<Integer> imageList;

    public ImagePagerAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // Inflar el layout item_image_pager.xml
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_pager, container, false);

        // Configurar el ImageView con la imagen correspondiente
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(imageList.get(position));

        // Añadir la vista al contenedor
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}


