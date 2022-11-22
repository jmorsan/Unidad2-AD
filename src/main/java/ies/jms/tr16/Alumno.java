package ies.jms.tr16;

public class Alumno {

    private String nombre;
    private int edad;
    private double calificacion;
    private boolean unidadesPendientes;

    public Alumno() {

    }

    public Alumno(String nombre, int edad, double calificacion, boolean unidadesPendientes) {
        this.nombre = nombre;
        this.edad = edad;
        this.calificacion = calificacion;
        this.unidadesPendientes = unidadesPendientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isUnidadesPendientes() {
        return unidadesPendientes;
    }

    public void setUnidadesPendientes(boolean unidadesPendientes) {
        this.unidadesPendientes = unidadesPendientes;
    }

    @Override
    public String toString() {
        return
                "Nombre: " + nombre +
                ", Edad: " + edad +
                ", Calificacion: " + calificacion +
                ", Unidades Pendientes: " + unidadesPendientes ;
    }
}
