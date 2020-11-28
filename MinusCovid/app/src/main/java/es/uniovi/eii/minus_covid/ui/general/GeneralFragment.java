package es.uniovi.eii.minus_covid.ui.general;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.util.ApiConection;
import es.uniovi.eii.minus_covid.util.ComunidadDto;
import es.uniovi.eii.minus_covid.util.Parser;
import es.uniovi.eii.minus_covid.util.RecyclerView_Adapter;

public class GeneralFragment extends Fragment {

    List<ComunidadDto> cds;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        List<ComunidadDto> cds = callApi();

        return cds;
    }

    public List<ComunidadDto> callApi(){
        try{
            JSONObject obj = new ApiConection(1, null).execute().get();
            List<ComunidadDto> dto = Parser.parse(obj);
            return dto;
        }catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}