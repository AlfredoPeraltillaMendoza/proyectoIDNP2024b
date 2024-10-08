package com.example.proyectoidnp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EdificacionesAdapter extends RecyclerView.Adapter<EdificacionesAdapter.EdificacionViewHolder> {

    private List<String> edificacionesList;

    public EdificacionesAdapter(List<String> edificacionesList) {
        this.edificacionesList = edificacionesList;
    }

    @NonNull
    @Override
    public EdificacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edificacion, parent, false);
        return new EdificacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EdificacionViewHolder holder, int position) {
        String edificacion = edificacionesList.get(position);
        holder.edificacionName.setText(edificacion);
    }

    @Override
    public int getItemCount() {
        return edificacionesList.size();
    }

    public static class EdificacionViewHolder extends RecyclerView.ViewHolder {

        public TextView edificacionName;

        public EdificacionViewHolder(@NonNull View itemView) {
            super(itemView);
            edificacionName = itemView.findViewById(R.id.edificacion_name);
        }
    }
}
