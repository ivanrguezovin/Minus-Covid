package es.uniovi.eii.minus_covid.ui.general;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.uniovi.eii.minus_covid.R;

public class View_Holder extends RecyclerView.ViewHolder {

    TextView nombre;

    TextView totalCasos;
    TextView totalCurados;
    TextView totalFallecidos;
    TextView nuevosCasos;
    TextView nuevosFallecidos;


    View_Holder(View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.nombre);
        totalCasos = (TextView) itemView.findViewById(R.id.totalCasos);
        totalCurados = (TextView) itemView.findViewById(R.id.totalCurados);
        totalFallecidos = (TextView) itemView.findViewById(R.id.totalFallecidos);
        nuevosCasos = (TextView) itemView.findViewById(R.id.nuevosCasos);
        nuevosFallecidos = (TextView) itemView.findViewById(R.id.nuevosFallecidos);
    }
}
