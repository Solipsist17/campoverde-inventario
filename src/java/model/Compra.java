
package model;

import java.util.Date;

public class Compra {
    private int id;
    private double precio;
    private int cantidad;
    private Date fecha_comopra;
    private Proveedor proveedor;
    private Empleado empleado;
    private Producto producto;

    public Compra(int id, double precio, int cantidad, Date fecha_comopra, Proveedor proveedor, Empleado empleado, Producto producto) {
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha_comopra = fecha_comopra;
        this.proveedor = proveedor;
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

    public Date getFecha_comopra() {
        return fecha_comopra;
    }

    public void setFecha_comopra(Date fecha_comopra) {
        this.fecha_comopra = fecha_comopra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
