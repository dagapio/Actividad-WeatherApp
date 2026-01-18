package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adaptador para mostrar el pronóstico diario en el RecyclerView.
 */
public class AdaptadorPronosticoDia extends RecyclerView.Adapter<AdaptadorPronosticoDia.ViewHolderPronostico> {

    // Lista de pronósticos
    private List<PronosticoDia> mPronosticos;

    /**
     * Constructor.
     * 
     * @param pronosticos Lista de pronósticos.
     */
    public AdaptadorPronosticoDia(List<PronosticoDia> pronosticos) {
        this.mPronosticos = pronosticos;
    }

    @NonNull
    @Override
    public ViewHolderPronostico onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_day_forecast, parent, false);
        return new ViewHolderPronostico(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPronostico holder, int position) {
        PronosticoDia actual = mPronosticos.get(position);
        holder.textoDia.setText(actual.getNombreDia());
        holder.textoTemp.setText(String.format("%.1f°C", actual.getTemperatura()));

        String condicion = actual.getCondicion().toLowerCase();
        if (condicion.contains("sol") || condicion.contains("sunny")) {
            holder.imagenIcono.setImageResource(R.drawable.ic_sun);
        } else if (condicion.contains("lluvia") || condicion.contains("rain")) {
            holder.imagenIcono.setImageResource(R.drawable.ic_rain);
        } else if (condicion.contains("nublado") || condicion.contains("cloud")) {
            holder.imagenIcono.setImageResource(R.drawable.ic_cloud);
        } else {
            holder.imagenIcono.setImageResource(R.drawable.ic_sun);
        }
    }

    @Override
    public int getItemCount() {
        return mPronosticos != null ? mPronosticos.size() : 0;
    }

    /**
     * ViewHolder para los elementos de pronóstico.
     */
    static class ViewHolderPronostico extends RecyclerView.ViewHolder {
        private final TextView textoDia;
        private final TextView textoTemp;
        private final ImageView imagenIcono;

        public ViewHolderPronostico(@NonNull View itemView) {
            super(itemView);
            textoDia = itemView.findViewById(R.id.textDay);
            textoTemp = itemView.findViewById(R.id.textDayTemp);
            imagenIcono = itemView.findViewById(R.id.imageDayWeather);
        }
    }
}
