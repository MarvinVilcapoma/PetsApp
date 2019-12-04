package com.integrador.petsapp.models;

public class Usuario {

    private Integer id;
    private String nombres;
    private String correo;
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
