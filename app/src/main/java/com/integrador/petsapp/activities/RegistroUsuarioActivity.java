package com.integrador.petsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.integrador.petsapp.R;

public class RegistroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
    }

    public void doLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
