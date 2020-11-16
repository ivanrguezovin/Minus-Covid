package es.uniovi.eii.minus_covid.ui.mapa.datos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

    BarChart barChart;
    BarDataSet barDataSet;

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
        if(!dto.total_casos_acumulado.equals("null"))
            totalCasos.setText(dto.total_casos_acumulado);
        else
            totalCasos.setText(R.string.noData);

        totalCurados = root.findViewById(R.id.totalCurados);
        if(!dto.total_curados_acumulado.equals("null"))
            totalCurados.setText(dto.total_curados_acumulado);
        else
            totalCurados.setText(R.string.noData);


        totalInfectados = root.findViewById(R.id.totalInfectados);
        if(!dto.total_infectados_acumulado.equals("null"))
            totalInfectados.setText(dto.total_infectados_acumulado);
        else
            totalInfectados.setText(R.string.noData);

        totalFallecidos = root.findViewById(R.id.totalFallecidos);
        if(!dto.total_fallecidos_acumulado.equals("null"))
            totalFallecidos.setText(dto.total_fallecidos_acumulado);
        else
            totalFallecidos.setText(R.string.noData);

        totalEnUCI = root.findViewById(R.id.totalEnUCI);
        if( !dto.total_uci_acumulado.equals("null"))
            totalEnUCI.setText(dto.total_uci_acumulado);
        else
            totalEnUCI.setText(R.string.noData);

        nuevosCasos = root.findViewById(R.id.nuevosCasos);
        if(!dto.nuevos_casos.equals("null"))
            nuevosCasos.setText(dto.nuevos_casos);
        else
            nuevosCasos.setText(R.string.noData);

        nuevosCurados = root.findViewById(R.id.nuevosCurados);
        if(!dto.nuevos_curados.equals("null"))
            nuevosCurados.setText(dto.nuevos_curados);
        else
            nuevosCurados.setText(R.string.noData);

        nuevosFallecidos = root.findViewById(R.id.nuevosFallecidos);
        if(!dto.nuevos_fallecidos.equals("null"))
            nuevosFallecidos.setText(dto.nuevos_fallecidos);
        else
            nuevosFallecidos.setText(R.string.noData);

        nuevosEnUCI = root.findViewById(R.id.nuevosEnUCI);
        if(!dto.nuevos_uci.equals("null"))
            nuevosEnUCI.setText(dto.nuevos_uci);
        else
            nuevosEnUCI.setText(R.string.noData);

        ubicacion = root.findViewById(R.id.about);
        if(!dto.nombre.equals("null"))
            ubicacion.setText(dto.nombre);
        else
            ubicacion.setText(R.string.noData);

        generarGrafico(root);

        return root;
    }

    private void generarGrafico(View root){
        // Enlazamos al XML
        lineChart = root.findViewById(R.id.lineChart);
        barChart = root.findViewById(R.id.barChart);

        // Creamos un set de datos
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for (int i = 0; i<11; i++){
            float y = (int) (Math.random() * 8) + 1;
            lineEntries.add(new Entry((float) i,(float)y));
        }

        // Creamos un set de datos
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
        for (int i = 0; i<11; i++){
            float y = (int) (Math.random() * 8) + 1;
            barEntries.add(new BarEntry((float) i,(float)y));
        }

        // Unimos los datos al data set
        lineDataSet = new LineDataSet(lineEntries, "Grafico de prueba");
        barDataSet = new BarDataSet(barEntries, "Barras prueba");

        // Asociamos al gráfico
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barChart.setData(barData);
    }
}