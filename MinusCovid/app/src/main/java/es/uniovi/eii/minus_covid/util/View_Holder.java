package es.uniovi.eii.minus_covid.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.uniovi.eii.minus_covid.R;

public class View_Holder extends RecyclerView.ViewHolder {

    TextView name;

    View_Holder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
    }
}
