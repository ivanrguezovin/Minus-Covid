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
import androidx.lifecycle.ViewModelProviders;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.ui.mapa.datos.DataFragment;
import es.uniovi.eii.minus_covid.util.ApiConection;
import es.uniovi.eii.minus_covid.util.ComunidadDto;
import es.uniovi.eii.minus_covid.util.Parser;

public class MapFragment extends Fragment {

    private MapViewModel mapViewModel;

    private HashMap<String,String> comunidades = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        generarHash();

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

    private void generarHash(){
        comunidades.put("Andalucía","andalucia");
        comunidades.put("Aragón","aragon");
        comunidades.put("Asturias","asturias");
        comunidades.put("Cantabria","cantabria");
        comunidades.put("Castilla-La Mancha","castilla-la_mancha");
        comunidades.put("Castilla y León","castilla_y_león");
        comunidades.put("Cataluña","cataluna");
        comunidades.put("Ceuta","ceuta");
        comunidades.put("Comunidad de Madrid","madrid");
        comunidades.put("Comunidad Foral de Navarra","navarra");
        comunidades.put("Comunidad Valenciana","c_valenciana");
        comunidades.put("Extremadura","extremadura");
        comunidades.put("Galicia","galicia");
        comunidades.put("Islas Baleares","baleares");
        comunidades.put("Islas Canarias","canarias");
        comunidades.put("La Rioja","la_rioja");
        comunidades.put("Melilla","melilla");
        comunidades.put("País Vasco","pais_vasco");
        comunidades.put("Región de Murcia","murcia");
    }

    private void searchLocationData(){
        DataFragment fr=new DataFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_main,fr)
                .addToBackStack(null)
                .commit();
        ComunidadDto dto = callApi();
    }

    public ComunidadDto callApi(){ //Mover este metodo a donde se llame a la api para obtener los datos
        try{
            JSONObject obj = new ApiConection().execute("Asturias").get();
            ComunidadDto dto = Parser.parse(obj);
            return dto;
        }catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}