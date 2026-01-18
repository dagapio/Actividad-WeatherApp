package com.example.weatherapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entidad que representa una ciudad en la base de datos.
 * La tabla se llamará "tabla_ciudad".
 */
@Entity(tableName = "tabla_ciudad")
public class Ciudad {

    // Identificador único auto-generado para cada ciudad
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Nombre de la ciudad
    private String nombre;
    // Temperatura actual
    private double temperatura;
    // Condición climática (ej. "Soleado", "Nublado", "Lluvioso")
    private String condicion;

    /**
     * Constructor para crear una nueva instancia de Ciudad.
     * 
     * @param nombre      El nombre de la ciudad.
     * @param temperatura La temperatura de la ciudad.
     * @param condicion   La condición climática textual.
     */
    public Ciudad(String nombre, double temperatura, String condicion) {
        this.nombre = nombre;
        this.temperatura = temperatura;
        this.condicion = condicion;
    }

    // Métodos Getter y Setter para acceder y modificar los datos

    /**
     * Obtiene el ID de la ciudad.
     * 
     * @return El ID único.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la ciudad.
     * 
     * @param id El nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la ciudad.
     * 
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la temperatura.
     * 
     * @return La temperatura.
     */
    public double getTemperatura() {
        return temperatura;
    }

    /**
     * Obtiene la condición climática.
     * 
     * @return La condición (texto).
     */
    public String getCondicion() {
        return condicion;
    }
}
