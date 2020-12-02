package es.uniovi.eii.minus_covid.ui.general;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.ui.mapa.datos.DataFragment;
import es.uniovi.eii.minus_covid.util.ApiConection;
import es.uniovi.eii.minus_covid.util.ComunidadDto;
import es.uniovi.eii.minus_covid.util.ComunidadFechaDto;
import es.uniovi.eii.minus_covid.util.Parser;
import es.uniovi.eii.minus_covid.util.RecyclerItemClickListener;
import es.uniovi.eii.minus_covid.util.RecyclerView_Adapter;

public class GeneralFragment extends Fragment {

    List<ComunidadDto> cds = generarComunidades();
    private HashMap<String, Integer> comunidades = new HashMap<>();
    RecyclerView recyclerView;
    RecyclerView_Adapter adapter;
    EditText search;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        generarHash();

        View root = inflater.inflate(R.layout.fragment_general, container, false);
        search = root.findViewById(R.id.search);
        recyclerView = root.findViewById(R.id.recyclerView);
        adapter = new RecyclerView_Adapter(cds, getActivity().getApplication());

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        searchLocationData(position);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return root;
    }

    private void filter(String s){
        List<ComunidadDto> coms = new ArrayList<ComunidadDto>();

        for(ComunidadDto c: cds){
            if(c.nombre.toLowerCase().contains(s.toLowerCase())){
                coms.add(c);
            }
        }
        actualizarHash(coms);
        adapter.filterlist(coms);
    }

    private void actualizarHash(List<ComunidadDto> coms){
        int contador=0;
        HashMap<String, Integer> cas =new HashMap<String, Integer>();
        for(ComunidadDto c: coms){
            for (Map.Entry<String, Integer> entry : comunidades.entrySet()) {
                String key = entry.getKey();
                if(c.nombre.equals(key)){
                    cas.put(key, contador);
                    contador++;
                }
            }
        }
        comunidades = cas;
    }

    private void generarHash() {
        comunidades.put("Aragón", 0);
        comunidades.put("Canarias", 1);
        comunidades.put("País Vasco", 2);
        comunidades.put("Baleares", 3);
        comunidades.put("Castilla y León", 4);
        comunidades.put("Cataluña", 5);
        comunidades.put("C. Valenciana", 6);
        comunidades.put("Ceuta", 7);
        comunidades.put("La Rioja", 8);
        comunidades.put("Navarra", 9);
        comunidades.put("Andalucía", 10);
        comunidades.put("Castilla-La Mancha", 11);
        comunidades.put("Murcia", 12);
        comunidades.put("Cantabria", 13);
        comunidades.put("Galicia", 14);
        comunidades.put("Melilla", 15);
        comunidades.put("Asturias", 16);
        comunidades.put("Madrid", 17);
        comunidades.put("Extremadura", 18);
    }

    private void searchLocationData(int position) {
        DataFragment fr = new DataFragment();

        String id = "";

        for (Map.Entry<String, Integer> entry : comunidades.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if(position == value){
                id = key;
            }
        }

        if(id != ""){
            List<ComunidadDto> listDto = callApi(1, id);
            Bundle datosAEnviar = new Bundle();

            ComunidadFechaDto comDto = new ComunidadFechaDto();
            comDto.listaFechas = callApi(2, id);
            datosAEnviar.putParcelable("dtoFechas", comDto);
            for (ComunidadDto dto : listDto) {
                if (dto.nombre.equals(id)) {
                    datosAEnviar.putParcelable("dto", dto);
                    break;
                }
            }


            fr.setArguments(datosAEnviar);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_main, fr)
                    .addToBackStack(null)
                    .commit();
        }
    }



    private List<ComunidadDto> generarComunidades(){
        List<ComunidadDto> cds = callApi(1,null);

        return cds;
    }

    public List<ComunidadDto> callApi(int option, String com) {
        try {
            if(option==1){
                JSONObject obj = new ApiConection(option, com).execute().get();
                List<ComunidadDto> dto = Parser.parse(obj);
                return dto;
            }
            if(option==2){
                JSONObject obj = new ApiConection(option, com).execute().get();
                List<ComunidadDto> dto = Parser.parserComunidadFechas(obj);
                return dto;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}