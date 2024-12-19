package com.example.proyectoidnp;

public class Comentario {
    private String userName; // Campo para el nombre del usuario
    private String comentario; // Campo para el texto del comentario
    private float rating; // Campo para la calificación

    // Constructor
    public Comentario(String userName, String comentario, float rating) {
        this.userName = userName;
        this.comentario = comentario;
        this.rating = rating;
    }

    // Getter para el nombre del usuario
    public String getUserName() {
        return userName;
    }

    // Setter para el nombre del usuario
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Getter para el comentario
    public String getComentario() {
        return comentario;
    }

    // Setter para el comentario
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // Getter para la calificación
    public float getRating() {
        return rating;
    }

    // Setter para la calificación
    public void setRating(float rating) {
        this.rating = rating;
    }
}

