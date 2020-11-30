package es.uniovi.eii.minus_covid.ui.general;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    List<ComunidadDto> cds;
    private HashMap<String, String> comunidades = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        List<ComunidadDto> cds = generarComunidades();
        generarHash();

        View root = inflater.inflate(R.layout.fragment_general, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        searchLocationData(root);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        RecyclerView_Adapter adapter = new RecyclerView_Adapter(cds, getActivity().getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return root;
    }

    private void generarHash() {
        comunidades.put("Andalucía", "andalucia");
        comunidades.put("Aragón", "aragon");
        comunidades.put("Asturias", "asturias");
        comunidades.put("Cantabria", "cantabria");
        comunidades.put("Castilla-La Mancha", "castilla-la_mancha");
        comunidades.put("Castilla y León", "castilla_y_leon");
        comunidades.put("Cataluña", "cataluna");
        comunidades.put("Ceuta", "ceuta");
        comunidades.put("Madrid", "madrid");
        comunidades.put("Navarra", "navarra");
        comunidades.put("C. Valenciana", "c_valenciana");
        comunidades.put("Extremadura", "extremadura");
        comunidades.put("Galicia", "galicia");
        comunidades.put("Baleares", "baleares");
        comunidades.put("Canarias", "canarias");
        comunidades.put("La Rioja", "la_rioja");
        comunidades.put("Melilla", "melilla");
        comunidades.put("País Vasco", "pais_vasco");
        comunidades.put("Murcia", "murcia");
    }

    private void searchLocationData(View root) {
        DataFragment fr = new DataFragment();
        TextView t = root.findViewById(R.id.nombre);
        String id = String.valueOf(t.getText());
        List<ComunidadDto> listDto = callApi(1, id);
        Bundle datosAEnviar = new Bundle();

        ComunidadFechaDto comDto = new ComunidadFechaDto();
        comDto.listaFechas = callApi(2, id);
        datosAEnviar.putParcelable("dtoFechas", comDto);
        for (ComunidadDto dto : listDto) {
            if (dto.id.equals(comunidades.get(id))) {
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