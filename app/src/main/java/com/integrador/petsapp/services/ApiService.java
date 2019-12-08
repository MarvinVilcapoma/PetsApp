package com.integrador.petsapp.services;


import com.integrador.petsapp.models.Mascota;
import com.integrador.petsapp.models.Usuario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    String API_BASE_URL = "http://10.0.2.2:8080";

    @GET("/mascotas") //Ruta de la api http://10.0.2.2:8080/productos
    Call<List<Mascota>> findAll();

    @GET("/api/usuarios")
    Call<List<Usuario>> buscartodos();


    @POST("/api/usuarios")
    Call<Usuario> createUsuario(@Body Usuario usuarios);


    @Multipart
    @POST("/api/usuarios")
    Call<Usuario> createUsuario(@Part("nombre_usu") RequestBody nombre_usu,
                                  @Part("correo_usu") RequestBody correo_usu,
                                  @Part("password_usu") RequestBody password_usu
    );

    @FormUrlEncoded
    @POST("/auth/login")
    Call<Usuario> login(@Field("correo_usu") String correo_usu,
                        @Field("password_usu") String password_usu);


    @GET("/api/mascotas/name/{nombre}")
    Call<List<Mascota>> FindByName(@Path("nombre") String nombre);

}
