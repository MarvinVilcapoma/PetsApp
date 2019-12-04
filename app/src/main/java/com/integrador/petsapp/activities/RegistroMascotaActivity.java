package com.integrador.petsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.integrador.petsapp.R;

public class RegistroMascotaActivity extends AppCompatActivity {

    private EditText nombremascotatxt;
    private EditText razamascotatxt;
    private EditText edadmascotatxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        nombremascotatxt=findViewById(R.id.et_nombremascota);
        razamascotatxt=findViewById(R.id.et_razamascota);
        edadmascotatxt=findViewById(R.id.et_edadmascota);
    }
    public void registerPet(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
