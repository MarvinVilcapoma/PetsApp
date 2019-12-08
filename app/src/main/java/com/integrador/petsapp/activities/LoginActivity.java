package com.integrador.petsapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.integrador.petsapp.R;
import com.integrador.petsapp.models.ApiError;
import com.integrador.petsapp.models.Usuario;
import com.integrador.petsapp.services.ApiService;
import com.integrador.petsapp.services.ApiServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText correotext;
    private EditText contrasenatxt;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correotext=findViewById(R.id.et_correo);
        contrasenatxt=findViewById(R.id.et_contrasena);
        btnIngresar=findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    login();
            }
        });
        loadLastUsername();

        verifyLoginStatus();
    }

    public void Registro(View view) {
        startActivity(new Intent(this, RegistroUsuarioActivity.class));
    }

    public void doLogin(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void login(){
        String correo_usu = correotext.getText().toString();
        String password_usu = contrasenatxt.getText().toString();

        if(correo_usu.isEmpty()){
            Toast.makeText(this, "Ingrese el nombre de usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password_usu.isEmpty()){
            Toast.makeText(this, "Ingrese el password", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<Usuario> call = service.login(correo_usu, password_usu);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) { // code 200
                    Usuario usuario = response.body();
                    Log.d(TAG, "usuario" + usuario);

                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    sp.edit()
                            .putString("correo", usuario.getCorreo_usu())
                            .putBoolean("islogged", true)
                            .commit();

                    // Go Main Activity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                    Toast.makeText(LoginActivity.this, "Bienvenido " + usuario.getNombre_usu(), Toast.LENGTH_LONG).show();

                }else{
                    ApiError error = ApiServiceGenerator.parseError(response);
                    Toast.makeText(LoginActivity.this, "onError:" + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "onFailure: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void loadLastUsername(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String correo_usu = sp.getString("username", null);
        if(correo_usu != null){
            correotext.setText(correo_usu);
        }
    }

    private void verifyLoginStatus(){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean islogged = sp.getBoolean("islogged", false);

        if(islogged){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
