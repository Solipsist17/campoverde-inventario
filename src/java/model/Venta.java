package model;

import java.util.Date;

public class Venta {
    private int id;
    private double precio;
    private int cantidad;
    private Date fechaVenta;
    private Cliente cliente;
    private Empleado empleado;
    private Producto producto;

    public Venta(int id, double precio, int cantidad, Date fechaVenta, Cliente cliente, Empleado empleado, Producto producto) {
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.empleado = empleado;
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
