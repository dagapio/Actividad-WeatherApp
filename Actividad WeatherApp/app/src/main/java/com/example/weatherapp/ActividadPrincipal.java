package com.example.weatherapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Actividad Principal de la aplicación.
 * Muestra una lista de ciudades con su clima actual.
 */
public class ActividadPrincipal extends AppCompatActivity {

    private BaseDeDatosClima mBaseDatos;
    private RecyclerView mRecyclerView;
    private AdaptadorCiudad mAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // título de la ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("WEATHER APP");
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdaptador = new AdaptadorCiudad(null);
        mRecyclerView.setAdapter(mAdaptador);

        // Inicializar Base de Datos
        mBaseDatos = BaseDeDatosClima.obtenerBaseDeDatos(this);

        // Observar Datos mediante LiveData
        mBaseDatos.daoCiudad().obtenerCiudadesAlfabetizadas().observe(this, new Observer<List<Ciudad>>() {
            @Override
            public void onChanged(List<Ciudad> ciudades) {
                // Actualizar la copia en caché de las ciudades en el adaptador
                mAdaptador.setCiudades(ciudades);
            }
        });
    }
}
