package es.uniovi.eii.minus_covid.ui.general;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.util.ComunidadDto;
import es.uniovi.eii.minus_covid.util.RecyclerView_Adapter;

public class GeneralFragment extends Fragment {

    List<ComunidadDto> cds;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        List<ComunidadDto> cds = generarComunidades();

        View root = inflater.inflate(R.layout.fragment_general, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(cds, getActivity().getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return root;
    }

    private List<ComunidadDto> generarComunidades(){
        List<ComunidadDto> cds = new ArrayList<ComunidadDto>();
        cds.add(new ComunidadDto("Andalucía"));
        cds.add(new ComunidadDto("Asturias"));
        cds.add(new ComunidadDto("Cataluña"));
        cds.add(new ComunidadDto("Murcia"));

        return cds;
    }
}