package com.example.weatherapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Base de datos principal de la aplicación utilizando Room.
 * Define las entidades y la versión de la base de datos.
 */
@Database(entities = { Ciudad.class }, version = 1, exportSchema = false)
public abstract class BaseDeDatosClima extends RoomDatabase {

    // Método abstracto para obtener el DAO de Ciudad
    public abstract DaoCiudad daoCiudad();

    // Instancia única (Singleton) de la base de datos
    private static volatile BaseDeDatosClima INSTANCIA;
    private static final int NUMERO_DE_HILOS = 4;
    // Ejecutor para operaciones de base de datos en segundo plano
    static final ExecutorService ejecutorEscrituraBaseDatos = Executors.newFixedThreadPool(NUMERO_DE_HILOS);

    /**
     * Obtiene la instancia de la base de datos. Si no existe, la crea.
     * 
     * @param context El contexto de la aplicación.
     * @return La instancia única de BaseDeDatosClima.
     */
    static BaseDeDatosClima obtenerBaseDeDatos(final Context context) {
        if (INSTANCIA == null) {
            synchronized (BaseDeDatosClima.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            BaseDeDatosClima.class, "base_datos_clima") // Nombre del archivo de BD
                            .addCallback(callbackBaseDatosRoom)
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    // Callback para poner datos a la base de datos con datos iniciales
    private static RoomDatabase.Callback callbackBaseDatosRoom = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Poblar la base de datos en un hilo secundario
            ejecutorEscrituraBaseDatos.execute(() -> {
                DaoCiudad dao = INSTANCIA.daoCiudad();
                dao.borrarTodo();

                // Datos de ejemplo
                Ciudad ciudad = new Ciudad("Madrid", 25.0, "Soleado");
                dao.insertar(ciudad);

                ciudad = new Ciudad("Barcelona", 22.5, "Nublado");
                dao.insertar(ciudad);

                ciudad = new Ciudad("Galicia", 18.0, "Lluvia");
                dao.insertar(ciudad);

                ciudad = new Ciudad("Sevilla", 30.0, "Soleado");
                dao.insertar(ciudad);
            });
        }
    };
}
