package com.example.proyectoidnp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "UserProfile.db";
    private static final int DATABASE_VERSION = 2;

    // Tabla de perfil
    public static final String TABLE_PROFILE = "profile";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";

    // Tabla de comentarios
    public static final String TABLE_COMENTARIOS = "comentarios";
    public static final String COLUMN_COMENTARIO_ID = "comentario_id";
    public static final String COLUMN_ATRACTIVO = "atractivo";
    public static final String COLUMN_COMENTARIO = "comentario";
    public static final String COLUMN_RATING = "rating";
    // Constante para el nombre del usuario en la tabla de comentarios
    public static final String COLUMN_USER_NAME = "user_name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de perfil
        String CREATE_TABLE_PROFILE = "CREATE TABLE " + TABLE_PROFILE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PHONE + " TEXT)";
        db.execSQL(CREATE_TABLE_PROFILE);

        // Crear tabla de comentarios
        String CREATE_TABLE_COMENTARIOS = "CREATE TABLE " + TABLE_COMENTARIOS + "(" +
                COLUMN_COMENTARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ATRACTIVO + " TEXT," +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_COMENTARIO + " TEXT," +
                COLUMN_RATING + " REAL)";
        db.execSQL(CREATE_TABLE_COMENTARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String CREATE_TABLE_COMENTARIOS = "CREATE TABLE " + TABLE_COMENTARIOS + "(" +
                    COLUMN_COMENTARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ATRACTIVO + " TEXT," +
                    COLUMN_USER_NAME + " TEXT," +
                    COLUMN_COMENTARIO + " TEXT," +
                    COLUMN_RATING + " REAL)";
            db.execSQL(CREATE_TABLE_COMENTARIOS);
        }
    }


    // Métodos para la tabla de perfil
    public boolean saveProfile(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);

        // Verificar si ya existe un registro
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PROFILE, null);
        if (cursor.moveToFirst()) {
            // Actualizar registro existente
            int result = db.update(TABLE_PROFILE, values, COLUMN_ID + "=?", new String[]{"1"});
            cursor.close();
            return result != -1;
        } else {
            // Insertar nuevo registro
            long result = db.insert(TABLE_PROFILE, null, values);
            cursor.close();
            return result != -1;
        }
    }

    public Cursor getProfile() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PROFILE, null);
    }

    // Métodos para la tabla de comentarios
    public boolean saveComentario(String atractivo, String userName, String comentario, float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ATRACTIVO, atractivo); // Nombre del atractivo
        values.put(COLUMN_USER_NAME, userName); // Nombre del usuario
        values.put(COLUMN_COMENTARIO, comentario); // Texto del comentario
        values.put(COLUMN_RATING, rating); // Calificación
        long result = db.insert(TABLE_COMENTARIOS, null, values);
        return result != -1;
    }

    public Cursor getComentarios(String atractivo) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_COMENTARIOS + " WHERE " + COLUMN_ATRACTIVO + "=?", new String[]{atractivo});
    }
    // Método para obtener el último nombre de usuario registrado
    public String getLastUserName() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_NAME + " FROM " + TABLE_PROFILE + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1", null);
        if (cursor != null && cursor.moveToFirst()) {
            String userName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            cursor.close();
            return userName;
        }
        return "Usuario Desconocido"; // Valor por defecto si no hay usuarios registrados
    }

}
