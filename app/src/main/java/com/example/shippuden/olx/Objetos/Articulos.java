package com.example.shippuden.olx.Objetos;

/**
 * Created by Shippuden on 06/06/2017.
 */

public class Articulos {
    private String Categoria;
    private String Descripcion;
    private String Valor;
    private String Fecha;
    private String ImgagenProd;
    private String NombreContacto;
    private String Celular;
    private String Direccion;
    private String Correo;
    private String FotoPerfil;

    public Articulos() {
    }

    public Articulos(String categoria, String descripcion, String valor, String fecha, String imgagenProd, String nombreContacto, String celular, String direccion, String correo, String fotoPerfil) {
        Categoria = categoria;
        Descripcion = descripcion;
        Valor = valor;
        Fecha = fecha;
        ImgagenProd=imgagenProd;
        NombreContacto=nombreContacto;
        Celular=celular;
        Direccion=direccion;
        Correo=correo;
        FotoPerfil=fotoPerfil;
    }

    //Construcctor sin categoria
    public Articulos(String descripcion, String valor, String fecha, String imgagenProd, String nombreContacto, String celular, String direccion, String correo, String fotoPerfil) {
        Descripcion = descripcion;
        Valor = valor;
        Fecha = fecha;
        ImgagenProd=imgagenProd;
        NombreContacto=nombreContacto;
        Celular=celular;
        Direccion=direccion;
        Correo=correo;
        FotoPerfil=fotoPerfil;
    }
    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
    public String getImgagenProd() {
        return ImgagenProd;
    }

    public void setImgagenProd(String imgagenProd) {
        ImgagenProd = imgagenProd;
    }

    public String getNombreContacto() {
        return NombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        NombreContacto = nombreContacto;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFotoPerfil() {
        return FotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        FotoPerfil = fotoPerfil;
    }

}
