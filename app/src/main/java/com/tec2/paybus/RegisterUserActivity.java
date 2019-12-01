package com.tec2.paybus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterUserActivity extends AppCompatActivity {
    TextInputEditText txtNombre;
    TextInputEditText txtApPaterno;
    TextInputEditText txtApMaterno;
    TextInputEditText txtFechaNac;
    AutoCompleteTextView editTextFilledExposedDropdown;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        String[] SEXOS = new String[]{"Masculino", "Femenino", "Otro"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        R.layout.dropdown_menu_popup_item,
                        SEXOS);

        editTextFilledExposedDropdown = findViewById(R.id.txtSexoInput);
        editTextFilledExposedDropdown.setAdapter(adapter);

        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(view -> registerUserData());

        txtNombre = findViewById(R.id.txtNombreImput);
        txtApPaterno = findViewById(R.id.txtApePatInput);
        txtApMaterno = findViewById(R.id.txtApeMatInput);
        txtFechaNac = findViewById(R.id.txtNacInput);

        user = FirebaseAuth.getInstance().getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void registerUserData() {
        Map<String, Object> docData = new HashMap<>();
        docData.put("nombre", txtNombre.getText().toString());
        docData.put("aPaterno", txtApPaterno.getText().toString());
        docData.put("aMaterno", txtApMaterno.getText().toString());
        docData.put("sexo", "Machote");
        docData.put("logged", new Timestamp(new Date()));
        docData.put("saldo", 0.0);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference newCityRef = firebaseFirestore.collection("users").document(user.getUid());

        newCityRef.set(docData)
                .addOnSuccessListener(aVoid -> {
                    Log.d("SAVE DATA", "DocumentSnapshot successfully written!");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                })
                .addOnFailureListener(e -> Snackbar.make(txtNombre.getRootView(), "Ocurrio un error con el registro", BaseTransientBottomBar.LENGTH_LONG).show());
    }
}
