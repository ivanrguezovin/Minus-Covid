package es.uniovi.eii.minus_covid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.uniovi.eii.minus_covid.R;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView info = root.findViewById(R.id.informacion);
        info.setText("Desde ella podremos: \n\t+ Ver un resumen de datos generales de las distintas comunidades de España." + "\n\t+ Buscar en el mapa la zona de interés para desplegar información más detallada.");
        return root;
    }
}