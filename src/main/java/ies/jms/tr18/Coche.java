package ies.jms.tr18;

public class Coche {

    private String marca;
    private String modelo;
    private String puertas;
    private int kilometros;
    private Motor motor;

    public Coche()
    {

    }

    public Coche(String marca, String modelo, String puertas, int kilometros, Motor motor) {
        this.marca = marca;
        this.modelo = modelo;
        this.puertas = puertas;
        this.kilometros = kilometros;
        this.motor = motor;
    }

    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPuertas() {
        return puertas;
    }

    public void setPuertas(String puertas) {
        this.puertas = puertas;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", puertas='" + puertas + '\'' +
                ", kilometros=" + kilometros +
                ", motor=" + motor +
                '}';
    }
}
