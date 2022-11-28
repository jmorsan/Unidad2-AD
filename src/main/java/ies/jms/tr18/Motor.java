package ies.jms.tr18;
public class Motor
{
    private int revoluciones;
    private String tipo;

    public Motor()
    {

    }

    public Motor(int revoluciones, String tipo) {
        this.revoluciones = revoluciones;
        this.tipo = tipo;
    }

    public int getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(int revoluciones) {
        this.revoluciones = revoluciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "revoluciones=" + revoluciones +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
