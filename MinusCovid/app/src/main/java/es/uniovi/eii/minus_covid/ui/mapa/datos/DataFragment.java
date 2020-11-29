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
import es.uniovi.eii.minus_covid.util.ComunidadFechaDto;

public class DataFragment extends Fragment {
    private ComunidadDto dto;
    private ComunidadFechaDto dtoFechas;

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

    LineChart lineChartInfectados;
    LineDataSet lineDataSetInfectados;

    BarChart barChartFallecidos;
    BarDataSet barDataSetFallecidos;

    BarChart barChartCurados;
    BarDataSet barDataSetCurados;

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
        dtoFechas = datosRecuperados.getParcelable("dtoFechas");


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
        lineChartInfectados = root.findViewById(R.id.lineChart);
        barChartFallecidos = root.findViewById(R.id.barChart);
        barChartCurados = root.findViewById(R.id.barChart2);

        // Creamos un set de datos
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for (int i = 0; i<7; i++){

            lineEntries.add(new Entry((float) i,Float.parseFloat(dtoFechas.listaFechas.get(i).total_infectados_acumulado)));
        }

        // Creamos un set de datos
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();

        for (int i = 0; i<7; i++){

            //Float.parseFloat(dtoFechas.listaFechas.get(i).total_infectados_acumulado))
            barEntries.add(new BarEntry((float) i, Float.parseFloat(dtoFechas.listaFechas.get(i).total_fallecidos_acumulado)));

        }

        ArrayList<BarEntry> barEntriesC = new ArrayList<BarEntry>();
        for (int i = 0; i<7; i++){
            barEntriesC.add(new BarEntry((float) i, Float.parseFloat(dtoFechas.listaFechas.get(i).nuevos_curados)));
        }

        // Unimos los datos al data set
        lineDataSetInfectados = new LineDataSet(lineEntries, "Infectados en los últimos  7 días");
        barDataSetFallecidos = new BarDataSet(barEntries, "Fallecidos en los últimos  7 días");
        barDataSetCurados = new BarDataSet(barEntriesC, "Curados en los últimos  7 días");

        // Asociamos al gráfico
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSetInfectados);
        lineChartInfectados.setData(lineData);

        BarData barData = new BarData();
        barData.addDataSet(barDataSetFallecidos);
        barChartFallecidos.setData(barData);

        BarData barData2 = new BarData();
        barData2.addDataSet(barDataSetCurados);
        barChartCurados.setData(barData2);
    }
}