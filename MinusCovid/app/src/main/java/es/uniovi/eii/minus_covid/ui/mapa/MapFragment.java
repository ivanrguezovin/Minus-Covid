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

import com.google.android.gms.maps.CameraUpdateFactory;
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
import java.util.Map;
import java.util.concurrent.ExecutionException;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.ui.mapa.datos.DataFragment;
import es.uniovi.eii.minus_covid.util.ApiConection;
import es.uniovi.eii.minus_covid.util.ComunidadDto;
import es.uniovi.eii.minus_covid.util.ComunidadFechaDto;
import es.uniovi.eii.minus_covid.util.Parser;

import static androidx.core.content.ContextCompat.checkSelfPermission;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private HashMap<String, ComunidadDto> comunidades = new HashMap<>();

    Context context;
    TextView selectCommunity;
    MapView mapView;
    GoogleMap map;


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
//        spinnerCommunity = root.findViewById(R.id.spinnerCommunity);
//        buttonSearch = root.findViewById(R.id.bt_search);
//
//        buttonSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchLocationData(comunidades.get(spinnerCommunity.getSelectedItem().toString()).id);
//            }
//        });
        return root;
    }


    private void generarHash() {
        comunidades.put("Andalucía", new ComunidadDto("andalucia", 37.463274379, -4.5756251361));
        comunidades.put("Aragón", new ComunidadDto("aragon", 41.5195355493, -0.659846411976));
        comunidades.put("Asturias", new ComunidadDto("asturias", 43.292357861, -5.99350932547));
        comunidades.put("Cantabria", new ComunidadDto("cantabria", 43.1975195366, -4.03001213183));
        comunidades.put("Castilla-La Mancha", new ComunidadDto("castilla-la_mancha", 39.5809896328, -3.00462777209));
        comunidades.put("Castilla y León", new ComunidadDto("castilla_y_leon", 41.7543962127, -4.78188694026));
        comunidades.put("Cataluña", new ComunidadDto("cataluna", 41.7985537834, 1.52905348544));
        comunidades.put("Ceuta", new ComunidadDto("ceuta", 35.8934069863, -5.34342403891));
        comunidades.put("Comunidad de Madrid", new ComunidadDto("madrid", 40.495082963, -3.71704006617));
        comunidades.put("Comunidad Foral de Navarra", new ComunidadDto("navarra", 42.6672011468, -1.6461117688));
        comunidades.put("Comunidad Valenciana", new ComunidadDto("c_valenciana", 39.4015584598, -0.554726732459));
        comunidades.put("Extremadura", new ComunidadDto("extremadura", 39.1914992537, -6.15082693044));
        comunidades.put("Galicia", new ComunidadDto("galicia", 42.7567966298, -7.91056344066));
        comunidades.put("Islas Baleares", new ComunidadDto("baleares", 39.5751889864, 2.91229172079));
        comunidades.put("Islas Canarias", new ComunidadDto("canarias", 28.339798593, -15.6720984172));
        comunidades.put("La Rioja", new ComunidadDto("la_rioja", 42.2748733608, -2.51703983986));
        comunidades.put("Melilla", new ComunidadDto("melilla", 35.2908279949, -2.95053552337));
        comunidades.put("País Vasco", new ComunidadDto("pais_vasco", 43.0433630599, -2.61681792149));
        comunidades.put("Región de Murcia", new ComunidadDto("murcia", 38.0023679133, -1.48575857531));
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

        map.setMinZoomPreference(5);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(35.495082963, -6.3)));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                searchLocationData(comunidades.get(marker.getTitle()).id);
                return false;
            }
        });

        for (Map.Entry<String, ComunidadDto> c: comunidades.entrySet()) {
            String clave = c.getKey();
            ComunidadDto com = c.getValue();
            LatLng latLng = new LatLng(com.lat, com.lng);
            googleMap.addMarker(new MarkerOptions().position(latLng).title(clave));
        }
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