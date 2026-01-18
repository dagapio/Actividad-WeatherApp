package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Actividad que muestra los detalles de una ciudad seleccionada.
 * Muestra un pronóstico de 5 días.
 */
public class ActividadDetalle extends AppCompatActivity {

    public static final String EXTRA_NOMBRE_CIUDAD = "com.example.weatherapp.NOMBRE_CIUDAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtener el nombre de la ciudad del Intent
        String nombreCiudad = "Ciudad";
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_NOMBRE_CIUDAD)) {
            nombreCiudad = intent.getStringExtra(EXTRA_NOMBRE_CIUDAD);
        }

        // Configurar el título de la Barra de Acción
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("WEATHER APP");
            getSupportActionBar().setSubtitle(nombreCiudad);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewForecast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Generar datos ficticios para los días
        List<PronosticoDia> pronosticos = generarPronosticos();
        AdaptadorPronosticoDia adaptador = new AdaptadorPronosticoDia(pronosticos);
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * Genera una lista de pronósticos ficticios para 5 días.
     * 
     * @return Lista de objetos PronosticoDia.
     */
    private List<PronosticoDia> generarPronosticos() {
        List<PronosticoDia> lista = new ArrayList<>();
        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
        String[] condiciones = { "Soleado", "Lluvia", "Nublado" };
        Random random = new Random();

        for (String dia : dias) {
            double temp = 15 + random.nextDouble() * 15; // Temperatura entre 15 y 30
            String cond = condiciones[random.nextInt(condiciones.length)];
            lista.add(new PronosticoDia(dia, temp, cond));
        }
        return lista;
    }
}
