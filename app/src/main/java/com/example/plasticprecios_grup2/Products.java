package com.example.plasticprecios_grup2;

public class Products {

    private String userName;
    private int stock;
    private int precio;
    private int valoracio;
    private String nombre;
    private String descripcion;

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

    public int getStock() {
        return stock;
    }

    public int getPrecio() {
        return precio;
    }

    public int getValoracio() {
        return valoracio;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setValoracio(int valoracio) {
        this.valoracio = valoracio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
