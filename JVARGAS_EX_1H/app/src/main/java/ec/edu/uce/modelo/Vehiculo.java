package ec.edu.uce.modelo;

import java.io.Serializable;
import java.util.Date;

public class Vehiculo implements Serializable {

    private String placa;
    private String marca;
    private Date fechaFabricacion;
    private Double costo;
    private Boolean matriculado;
    private String color;

    public Vehiculo() {

    }

    // public Vehiculo(String placa, String marca, Date fechaFabricacion, Double costo, Boolean matriculado, String color) {
        //this.placa = placa;
        //this.marca = marca;
        //this.fechaFabricacion = fechaFabricacion;
        //this.costo = costo;
        //this.matriculado = matriculado;
      //  this.color = color;
    //}
    public String getPlaca() {
        return placa;
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

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Boolean matriculado) {
        this.matriculado = matriculado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
