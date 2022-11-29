package com.example.plasticprecios_grup2;

public class Products {

    public String userName;
    public int precio;
    public String nombre;
    public String descripcion;

    @Override
    public String toString() {
        return "Products{" +
                "userName='" + userName + '\'' +
                ", precio=" + precio +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public Products(String userName, int preu, String nombre, String desc) {
        setUserName(userName);
        setPrecio(preu);
        setNombre(nombre);
        setDescripcion(desc);

    }

    public String getUserName() {
        return userName;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
