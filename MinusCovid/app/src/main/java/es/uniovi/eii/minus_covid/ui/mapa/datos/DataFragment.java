package es.uniovi.eii.minus_covid.ui.mapa.datos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import es.uniovi.eii.minus_covid.R;
import es.uniovi.eii.minus_covid.util.ComunidadDto;

public class DataFragment extends Fragment {
    private ComunidadDto dto;

    TextView totalCasos;
    TextView totalCurados;
    TextView totalInfectados;
    TextView totalFallecidos;
    TextView totalEnUCI;

    TextView nuevosCasos;
    TextView nuevosCurados;
    TextView nuevosFallecidos;
    TextView nuevosEnUCI;

    TextView ubicacion;

    LineChart lineChart;
    LineDataSet lineDataSet;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_data, container, false);
        Bundle datosRecuperados = getArguments();
        dto = datosRecuperados.getParcelable("dto");

        totalCasos = root.findViewById(R.id.totalCasos);
        totalCasos.setText(Integer.toString(dto.total_casos_acumulado));

        totalCurados = root.findViewById(R.id.totalCurados);
        totalCurados.setText(Integer.toString(dto.total_curados_acumulado));

        totalInfectados = root.findViewById(R.id.totalInfectados);
        totalInfectados.setText(Integer.toString(dto.total_infectados_acumulado));

        totalFallecidos = root.findViewById(R.id.totalFallecidos);
        totalFallecidos.setText(Integer.toString(dto.total_fallecidos_acumulado));

        totalEnUCI = root.findViewById(R.id.totalEnUCI);
        totalEnUCI.setText(Integer.toString(dto.total_uci_acumulado));

        nuevosCasos = root.findViewById(R.id.nuevosCasos);
        nuevosCasos.setText(Integer.toString(dto.nuevos_casos));

        nuevosCurados = root.findViewById(R.id.nuevosCurados);
        nuevosCurados.setText(Integer.toString(dto.nuevos_curados));

        nuevosFallecidos = root.findViewById(R.id.nuevosFallecidos);
        nuevosFallecidos.setText(Integer.toString(dto.nuevos_fallecidos));

        nuevosEnUCI = root.findViewById(R.id.nuevosEnUCI);
        nuevosEnUCI.setText(Integer.toString(dto.nuevos_uci));

        ubicacion = root.findViewById(R.id.about);
        ubicacion.setText(dto.nombre);

        generarGrafico(root);

        return root;
    }

    private void generarGrafico(View root){
        // Enlazamos al XML
        lineChart = root.findViewById(R.id.lineChart);

        // Creamos un set de datos
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for (int i = 0; i<11; i++){
            float y = (int) (Math.random() * 8) + 1;
            lineEntries.add(new Entry((float) i,(float)y));
        }

        // Unimos los datos al data set
        lineDataSet = new LineDataSet(lineEntries, "Grafico de prueba");

        // Asociamos al gráfico
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);
    }
}