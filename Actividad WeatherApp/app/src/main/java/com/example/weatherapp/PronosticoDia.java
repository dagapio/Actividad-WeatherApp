package com.example.weatherapp;

/**
 * Clase modelo para representar el pronóstico del tiempo de un día específico.
 * Se utiliza para mostrar la lista de pronósticos futuros.
 */
public class PronosticoDia {
    // Nombre del día (ej. "Lunes", "Martes")
    private String nombreDia;
    // Temperatura pronosticada para ese día
    private double temperatura;
    // Condición climática (ej. "Soleado")
    private String condicion;

    /**
     * Constructor para inicializar el pronóstico de un día.
     * 
     * @param nombreDia   Nombre del día de la semana.
     * @param temperatura Temperatura estimada.
     * @param condicion   Descripción del clima.
     */
    public PronosticoDia(String nombreDia, double temperatura, String condicion) {
        this.nombreDia = nombreDia;
        this.temperatura = temperatura;
        this.condicion = condicion;
    }

    /**
     * Obtiene el nombre del día.
     */
    public String getNombreDia() {
        return nombreDia;
    }

    /**
     * Obtiene la temperatura.
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * Obtiene la condición del clima.
     */
    public String getCondicion() {
        return condicion;
    }
}
