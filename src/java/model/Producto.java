package model;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double precio;
    private Categoria categoria;
    private Ubicacion ubicacion;

    public Producto(int id, String nombre, int stock, double precio, Categoria categoria, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
    }

    public Producto(String nombre, int stock, double precio, Categoria categoria, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public Producto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + ", categoria=" + categoria + ", ubicacion=" + ubicacion + '}';
    }
    
    
    
}
