package es.uniovi.eii.minus_covid.util;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import es.uniovi.eii.minus_covid.R;

public class RecyclerView_Adapter extends RecyclerView.Adapter<View_Holder> {

    List<ComunidadDto> list = Collections.emptyList();
    Context context;

    public RecyclerView_Adapter(List<ComunidadDto> data, Application application) {
        this.list = data;
        this.context = application;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.nombre.setText(list.get(position).nombre);

        holder.totalCasos.setText(list.get(position).total_casos_acumulado);
        holder.totalCurados.setText(list.get(position).total_curados_acumulado);
        holder.totalFallecidos.setText(list.get(position).total_fallecidos_acumulado);
        holder.nuevosCasos.setText(list.get(position).nuevos_casos);
        holder.nuevosFallecidos.setText(list.get(position).nuevos_fallecidos);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
