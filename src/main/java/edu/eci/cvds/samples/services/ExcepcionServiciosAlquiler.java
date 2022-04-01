package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception {
    public ExcepcionServiciosAlquiler(String mensaje,Exception e){
        super(mensaje,e);
    }
    public ExcepcionServiciosAlquiler(String mensaje){
        super(mensaje);
    }
}