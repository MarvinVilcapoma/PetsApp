package com.integrador.petsapp.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.integrador.petsapp.R;
import com.integrador.petsapp.models.Usuario;
import com.integrador.petsapp.services.ApiService;
import com.integrador.petsapp.services.ApiServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuarioActivity extends AppCompatActivity {

    private static final String TAG = RegistroUsuarioActivity.class.getSimpleName();

    private EditText nombrestxt;
    private EditText correotxt;
    private EditText contraseniatxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        nombrestxt=findViewById(R.id.et_nombres);
        correotxt=findViewById(R.id.et_correo);
        contraseniatxt=findViewById(R.id.et_contrasenia);

    }

    public void doLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void callRegister(View view) {
        String nombre_usu=nombrestxt.getText().toString();
        String correo_usu=correotxt.getText().toString();
        String password_usu=contraseniatxt.getText().toString();

        if (nombre_usu.isEmpty() || correo_usu.isEmpty() || password_usu.isEmpty()) {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuarios = new Usuario(nombre_usu,correo_usu,password_usu);
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Usuario> call;

        call=service.createUsuario(usuarios);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                try {
                    if(response.isSuccessful()) {

                        Usuario usuarios = response.body();
                        Log.d(TAG, "usuario: " + usuarios);

                        Toast.makeText(RegistroUsuarioActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                        setResult(RESULT_OK);

                        startActivity(new Intent(RegistroUsuarioActivity.this, LoginActivity.class));
                        finish();

                    }else{
                        throw new Exception(ApiServiceGenerator.parseError(response).getMessage());
                    }
                } catch (Throwable t) {
                    Log.e(TAG, "onThrowable: " + t.getMessage(), t);
                    Toast.makeText(RegistroUsuarioActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(RegistroUsuarioActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
