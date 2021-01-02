package es.uniovi.eii.minus_covid.ui.mapa;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.ui.mapa.datos.DataFragment;
import es.uniovi.eii.minus_covid.util.ApiConection;
import es.uniovi.eii.minus_covid.util.ComunidadDto;
import es.uniovi.eii.minus_covid.util.ComunidadFechaDto;
import es.uniovi.eii.minus_covid.util.Parser;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private HashMap<String, String> comunidades = new HashMap<>();

    Context context;
    TextView selectCommunity;
    Spinner spinnerCommunity;
    Button buttonSearch;
    MapView mapView;
    GoogleMap map;

    private Marker marcadorAsturias;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        generarHash();

        selectCommunity = root.findViewById(R.id.text_home);
        spinnerCommunity = root.findViewById(R.id.spinnerCommunity);
        buttonSearch = root.findViewById(R.id.bt_search);

//        buttonSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchLocationData();
//            }
//        });
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
        comunidades.put("Comunidad de Madrid", "madrid");
        comunidades.put("Comunidad Foral de Navarra", "navarra");
        comunidades.put("Comunidad Valenciana", "c_valenciana");
        comunidades.put("Extremadura", "extremadura");
        comunidades.put("Galicia", "galicia");
        comunidades.put("Islas Baleares", "baleares");
        comunidades.put("Islas Canarias", "canarias");
        comunidades.put("La Rioja", "la_rioja");
        comunidades.put("Melilla", "melilla");
        comunidades.put("País Vasco", "pais_vasco");
        comunidades.put("Región de Murcia", "murcia");
    }


    private void searchLocationData(String id) {
        DataFragment fr = new DataFragment();
        List<ComunidadDto> listDto = callApi(1, null);

        Bundle datosAEnviar = new Bundle();

//        String id = comunidades.get(spinnerCommunity.getSelectedItem().toString());
        ComunidadFechaDto comDto = new ComunidadFechaDto();
        comDto.listaFechas = callApi(2, id);
        System.out.println("--------------MAP FRAGMENT ----------" + comDto.listaFechas.get(0).toString());
        datosAEnviar.putParcelable("dtoFechas", comDto);
        for (ComunidadDto dto : listDto) {
            if (dto.id.equals(id)) {
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

    public List<ComunidadDto> callApi(int option, String com) {
        try {
            if (option == 1) {
                JSONObject obj = new ApiConection(option, com).execute().get();
                List<ComunidadDto> dto = Parser.parse(obj);
                return dto;
            }
            if (option == 2) {
                JSONObject obj = new ApiConection(option, com).execute().get();
                List<ComunidadDto> dto = Parser.parserComunidadFechas(obj);
                return dto;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = view.findViewById(R.id.mapView);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setUpMap(googleMap);

        if (validaPermisos()) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Aviso: Permisos Desactivados");
                dialog.setMessage("Debe aceptar los permisos de localizacion para el correcto funcionamiento de la app. onMapReady");
                dialog.create().show();
                return;
           }
           googleMap.setMyLocationEnabled(true);
           googleMap.getUiSettings().setMyLocationButtonEnabled(true);
      } //else {
//            final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(context);
//            alertOpciones.setTitle("No hay conexión a internet");
//            alertOpciones.setMessage("Conéctate a una red para poder acceder al mapa");
//            alertOpciones.setPositiveButton("Aceptar", (dialog, which) -> {
//            });
//            alertOpciones.create().show();
        //}
    }

    private void setUpMap(GoogleMap googleMap) {
        MapsInitializer.initialize(context);

        map = googleMap;

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                searchLocationData(comunidades.get(marker.getTitle()));
                return false;
            }
        });

        LatLng latLng = new LatLng(43.3549307, -5.8512431);
        marcadorAsturias = googleMap.addMarker(new MarkerOptions().position(latLng).title("Asturias").snippet("Prueba de  que puedo poner un marcador aquí"));
    }

    private boolean validaPermisos() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if ((checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            return true;
        }
        //requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            boolean permisos;
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permisos = true;
            } else {
                permisos = false;
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Aviso: Permisos Desactivados");
                dialog.setMessage("Debe aceptar los permisos de localizacion para el correcto funcionamiento de la app.");
                dialog.create().show();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

}