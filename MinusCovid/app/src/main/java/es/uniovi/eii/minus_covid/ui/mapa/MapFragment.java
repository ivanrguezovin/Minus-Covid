package es.uniovi.eii.minus_covid.ui.mapa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.ui.mapa.datos.DataFragment;

public class MapFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        final TextView selectCommunity = root.findViewById(R.id.text_home);
        final Spinner spinnerCommunity = root.findViewById(R.id.spinnerCommunity);
        final Button buttonSearch = root.findViewById(R.id.bt_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLocationData();
            }
        });
        return root;
    }

    private void searchLocationData(){
        DataFragment fr=new DataFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.drawer_layout,fr)
                .addToBackStack(null)
                .commit();

    }
}