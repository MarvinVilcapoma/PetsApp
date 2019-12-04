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

    @GET("/api/mascotas") //Ruta de la api http://10.0.2.2:8080/productos
    Call<List<Mascota>> findAll();

    @FormUrlEncoded
    @POST("/api/mascotas")
    Call<Mascota> createProducto(@Field("nombre") String nombre,
                                  @Field("precio") String precio,
                                  @Field("detalles") String detalles);
    @Multipart
    @POST("/api/mascotas")
    Call<Mascota> createProducto(@Part("nombre") RequestBody nombre,
                                  @Part("precio") RequestBody precio,
                                  @Part("detalles") RequestBody detalles,
                                  @Part MultipartBody.Part imagen
    );

    @DELETE("/api/mascotas/{id}")
    Call<ApiMessage> destroyProducto(@Path("id") Long id);

    @GET("/api/mascotas/{id}")
    Call<Mascota> showProducto(@Path("id") Long id);

    @FormUrlEncoded
    @POST("/auth/login")
    Call<Usuario> login(@Field("correo") String correo,
                        @Field("password") String password);

    @GET("/api/profile")
    Call<Usuario> getProfile();

    @GET("/api/productos/name/{nombre}")
    Call<List<Mascota>> FindByName(@Path("nombre") String nombre);

}
