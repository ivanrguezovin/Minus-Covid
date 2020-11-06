package es.uniovi.eii.minus_covid.ui.mapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.uniovi.eii.minus_covid.R;

public class MapFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView selectCommunity = root.findViewById(R.id.text_home);
        final Spinner spinnerCommunity = root.findViewById(R.id.spinnerCommunity);
        final Button buttonSearch = root.findViewById(R.id.bt_search);
        return root;
    }
}