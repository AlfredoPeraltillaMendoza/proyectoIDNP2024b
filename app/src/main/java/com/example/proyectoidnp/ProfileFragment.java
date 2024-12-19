package com.example.proyectoidnp;

import androidx.fragment.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private EditText editName, editEmail, editPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar la base de datos
        databaseHelper = new DatabaseHelper(getContext());

        // Obtener referencias a los elementos
        editName = view.findViewById(R.id.edit_name);
        editEmail = view.findViewById(R.id.edit_email);
        editPhone = view.findViewById(R.id.edit_phone);
        Button buttonSave = view.findViewById(R.id.button_save);

        // Cargar datos previos si existen
        loadProfileData();

        // Configurar listener para guardar datos
        buttonSave.setOnClickListener(v -> saveProfileData());

        return view;
    }

    private void loadProfileData() {
        Cursor cursor = databaseHelper.getProfile();
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE));

            // Llenar los campos con los datos existentes
            editName.setText(name);
            editEmail.setText(email);
            editPhone.setText(phone);
        }
        cursor.close();
    }

    private void saveProfileData() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String phone = editPhone.getText().toString();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSaved = databaseHelper.saveProfile(name, email, phone);
        if (isSaved) {
            Toast.makeText(getContext(), "Perfil guardado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error al guardar el perfil", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
