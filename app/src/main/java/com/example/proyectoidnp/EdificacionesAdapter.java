package com.example.proyectoidnp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class EdificacionesAdapter extends RecyclerView.Adapter<EdificacionesAdapter.ViewHolder> {

    private List<AtractivoTuristico> atractivosList;
    private Context context;

    public EdificacionesAdapter(Context context, List<AtractivoTuristico> atractivosList) {
        this.context = context;
        this.atractivosList = atractivosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_edificacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AtractivoTuristico atractivo = atractivosList.get(position);
        holder.nombreTextView.setText(atractivo.getNombre());
        holder.descripcionTextView.setText(atractivo.getDescripcion());
        holder.imagenImageView.setImageResource(atractivo.getImagenResId());

        // Configurar el OnClickListener para abrir DetalleAtractivoFragment
        holder.itemView.setOnClickListener(v -> {
            // Crear lista de imágenes con solo la imagen del atractivo seleccionado
            ArrayList<Integer> imagenes = new ArrayList<>();
            imagenes.add(atractivo.getImagenResId());

            // Crear instancia de DetalleAtractivoFragment con los datos del atractivo
            DetalleAtractivoFragment detalleFragment = DetalleAtractivoFragment.newInstance(
                    atractivo.getNombre(),
                    atractivo.getDescripcion(),
                    imagenes
            );

            // Iniciar la transacción del fragmento para mostrar DetalleAtractivoFragment
            FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, detalleFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return atractivosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenImageView;
        TextView nombreTextView;
        TextView descripcionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenImageView = itemView.findViewById(R.id.imagen_atractivo);
            nombreTextView = itemView.findViewById(R.id.nombre_atractivo);
            descripcionTextView = itemView.findViewById(R.id.descripcion_atractivo);
        }
    }
}



