package com.example.weatherapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Objeto de Acceso a Datos (DAO) para la tabla 'tabla_ciudad'.
 * Define los métodos para interactuar con la base de datos.
 */
@Dao
public interface DaoCiudad {

    /**
     * Inserta una nueva ciudad en la base de datos.
     * 
     * @param ciudad El objeto Ciudad a insertar.
     */
    @Insert
    void insertar(Ciudad ciudad);

    /**
     * Elimina todos los registros de la tabla de ciudades.
     */
    @Query("DELETE FROM tabla_ciudad")
    void borrarTodo();

    /**
     * Obtiene una lista de todas las ciudades ordenadas alfabéticamente por nombre.
     * Retorna un objeto LiveData para que la UI pueda observar cambios en los
     * datos.
     * 
     * @return LiveData conteniendo la lista de ciudades.
     */
    @Query("SELECT * FROM tabla_ciudad ORDER BY nombre ASC")
    LiveData<List<Ciudad>> obtenerCiudadesAlfabetizadas();
}
