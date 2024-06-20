package model;

public class Ubicacion {
    private int id;
    private String direccion;

    public Ubicacion(int id, String direccion) {
        this.id = id;
        this.direccion = direccion;
    }

    public Ubicacion(String direccion) {
        this.direccion = direccion;
    }

    public Ubicacion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
