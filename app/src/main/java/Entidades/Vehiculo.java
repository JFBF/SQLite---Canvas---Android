package Entidades;

/**
 * Clase que representa un Vehículo.
 * @author Felipe Bautista
 * @version 22/02/2018
 * @since 1.0
 */

public class Vehiculo {

    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int puertas;
    private String tipo;
    private String color;

    public Vehiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
