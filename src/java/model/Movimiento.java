package model;

import java.util.Date;

public class Movimiento {
    private int id;
    private Contacto contacto;
    private Empleado empleado;
    private Producto producto;
    private double precio;
    private int cantidad;
    private Date fechaMovimiento;
    private TipoMovimiento tipoMovimiento;

    public Movimiento(int id, Contacto contacto, Empleado empleado, Producto producto, double precio, int cantidad, Date fechaMovimiento, TipoMovimiento tipoMovimiento) {
        this.id = id;
        this.contacto = contacto;
        this.empleado = empleado;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
