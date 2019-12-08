package com.integrador.petsapp.models;

public class Usuario {

    private Long id_usu;
    private String nombre_usu;
    private String correo_usu;
    private String password_usu;
    private String mascota;

    public Usuario(String nombre_usu, String correo_usu, String password_usu) {
        this.nombre_usu = nombre_usu;
        this.correo_usu = correo_usu;
        this.password_usu = password_usu;
    }

    public Long getId_usu() {
        return id_usu;
    }

    public void setId_usu(Long id_usu) {
        this.id_usu = id_usu;
    }

    public String getNombre_usu() {
        return nombre_usu;
    }

    public void setNombre_usu(String nombre_usu) {
        this.nombre_usu = nombre_usu;
    }

    public String getCorreo_usu() {
        return correo_usu;
    }

    public void setCorreo_usu(String correo_usu) {
        this.correo_usu = correo_usu;
    }

    public String getPassword_usu() {
        return password_usu;
    }

    public void setPassword_usu(String password_usu) {
        this.password_usu = password_usu;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id_usu=" + id_usu +
                ", nombre_usu='" + nombre_usu + '\'' +
                ", correo_usu='" + correo_usu + '\'' +
                ", password_usu='" + password_usu + '\'' +
                ", mascota='" + mascota + '\'' +
                '}';
    }
}
