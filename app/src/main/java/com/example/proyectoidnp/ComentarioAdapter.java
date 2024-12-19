package com.example.proyectoidnp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioAdapter.ComentarioViewHolder> {

    private Context context;
    private List<Comentario> comentarios;

    public ComentarioAdapter(Context context, List<Comentario> comentarios) {
        this.context = context;
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        Comentario comentario = comentarios.get(position);
        holder.tvUserName.setText(comentario.getUserName()); // Muestra el nombre del usuario
        holder.tvComentario.setText(comentario.getComentario()); // Muestra el comentario
        holder.ratingBar.setRating(comentario.getRating()); // Muestra la calificación
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }

    public static class ComentarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName; // Nuevo campo para el nombre del usuario
        TextView tvComentario;
        RatingBar ratingBar;

        public ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_user_name); // Asigna el TextView para el nombre del usuario
            tvComentario = itemView.findViewById(R.id.tv_comentario);
            ratingBar = itemView.findViewById(R.id.rating_bar_item);
        }
    }
}
