package com.example.weatherapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador para mostrar la lista de ciudades en el RecyclerView.
 */
public class AdaptadorCiudad extends RecyclerView.Adapter<AdaptadorCiudad.ViewHolderCiudad> {

    // Lista de ciudades a mostrar
    private List<Ciudad> mCiudades;

    /**
     * Constructor del adaptador.
     * 
     * @param ciudades Lista inicial de ciudades.
     */
    public AdaptadorCiudad(List<Ciudad> ciudades) {
        this.mCiudades = ciudades;
    }

    /**
     * Actualiza la lista de ciudades y notifica al adaptador.
     * 
     * @param ciudades Nueva lista de ciudades.
     */
    public void setCiudades(List<Ciudad> ciudades) {
        this.mCiudades = ciudades;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderCiudad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño del elemento de la lista (item_city)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city, parent, false);
        return new ViewHolderCiudad(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCiudad holder, int position) {
        if (mCiudades != null) {
            Ciudad actual = mCiudades.get(position);
            holder.textoNombreCiudad.setText(actual.getNombre());
            holder.textoTemperatura.setText(String.format("%.1f°C", actual.getTemperatura()));
            holder.textoCondicion.setText(actual.getCondicion());

            // Establecer icono basado en la condición
            String condicion = actual.getCondicion().toLowerCase();
            if (condicion.contains("sol") || condicion.contains("sunny")) {
                holder.imagenClima.setImageResource(R.drawable.ic_sun);
            } else if (condicion.contains("lluvia") || condicion.contains("rain")) {
                holder.imagenClima.setImageResource(R.drawable.ic_rain);
            } else if (condicion.contains("nublado") || condicion.contains("cloud")) {
                holder.imagenClima.setImageResource(R.drawable.ic_cloud);
            } else {
                holder.imagenClima.setImageResource(R.drawable.ic_sun); // Por defecto
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mCiudades != null)
            return mCiudades.size();
        else
            return 0;
    }

    /**
     * Clase interna ViewHolder para mantener las referencias a las vistas.
     */
    class ViewHolderCiudad extends RecyclerView.ViewHolder {
        private final TextView textoNombreCiudad;
        private final TextView textoTemperatura;
        private final TextView textoCondicion;
        private final ImageView imagenClima;

        private ViewHolderCiudad(View itemView) {
            super(itemView);
            textoNombreCiudad = itemView.findViewById(R.id.textCityName);
            textoTemperatura = itemView.findViewById(R.id.textTemperature);
            textoCondicion = itemView.findViewById(R.id.textCondition);
            imagenClima = itemView.findViewById(R.id.imageWeather);

            // Configurar click listener para abrir detalles
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCiudades != null) {
                        int posicion = getAdapterPosition();
                        if (posicion != RecyclerView.NO_POSITION) {
                            Ciudad actual = mCiudades.get(posicion);
                            // Intent para abrir ActividadDetalle

                            Intent intent = new Intent(v.getContext(), ActividadDetalle.class);
                            intent.putExtra(ActividadDetalle.EXTRA_NOMBRE_CIUDAD, actual.getNombre());
                            v.getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }
}
