package es.uniovi.eii.minus_covid.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Parser {

    static Date date = new Date();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static List<ComunidadDto> parse(JSONObject data_obj) {
        ArrayList<ComunidadDto> dtoList = new ArrayList<ComunidadDto>();
        String formatedDate = sdf.format(date);
        try {
            data_obj = (JSONObject) data_obj.get(formatedDate);
            data_obj = (JSONObject) data_obj.get("countries");
            data_obj = (JSONObject) data_obj.get("Spain");
            JSONArray jsonarr = data_obj.getJSONArray("regions");
            for (int i = 0; i < jsonarr.length(); i++) {
                ComunidadDto dto = new ComunidadDto();
                dto.id = jsonarr.getJSONObject(i).getString("id");
                dto.nombre = jsonarr.getJSONObject(i).getString("name_es");

                dto.total_casos_acumulado = jsonarr.getJSONObject(i).getString("today_confirmed");
                dto.total_curados_acumulado = jsonarr.getJSONObject(i).getString("today_recovered");
                dto.total_infectados_acumulado = jsonarr.getJSONObject(i).getString("today_open_cases");
                dto.total_fallecidos_acumulado = jsonarr.getJSONObject(i).getString("today_deaths");
                dto.total_uci_acumulado = jsonarr.getJSONObject(i).getString("today_intensive_care");

                dto.nuevos_casos = jsonarr.getJSONObject(i).getString("today_new_confirmed");
                dto.nuevos_curados = jsonarr.getJSONObject(i).getString("today_new_recovered");
                dto.nuevos_fallecidos = jsonarr.getJSONObject(i).getString("today_new_deaths");
                dto.nuevos_uci = jsonarr.getJSONObject(i).getString("today_new_intensive_care");
                dtoList.add(dto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("PATATAAA");
        }
        return dtoList;
    }

    public static List<ComunidadDto> parserComunidadFechas(JSONObject data_obj) {

        ArrayList<ComunidadDto> dtoList = new ArrayList<ComunidadDto>();
        JSONObject copia = data_obj; //copia sin recorrer
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -7);
            Date dateBefore7Days = cal.getTime();
            String formateDateLastWeek = "";
            for (int j = 0; j < 7; j++) {
                formateDateLastWeek = sdf.format(dateBefore7Days);
                System.out.println("LA FECHA ES ESTA --------------------->" + formateDateLastWeek);

                data_obj = (JSONObject) data_obj.get(formateDateLastWeek);
                data_obj = (JSONObject) data_obj.get("countries");
                data_obj = (JSONObject) data_obj.get("Spain");
                JSONArray jsonarr = data_obj.getJSONArray("regions");
                for (int i = 0; i < jsonarr.length(); i++) {
                    ComunidadDto dto = new ComunidadDto();
                    dto.id = jsonarr.getJSONObject(i).getString("id");
                    dto.nombre = jsonarr.getJSONObject(i).getString("name_es");

                    dto.total_casos_acumulado = jsonarr.getJSONObject(i).getString("today_confirmed");
                    dto.total_curados_acumulado = jsonarr.getJSONObject(i).getString("today_recovered");
                    dto.total_infectados_acumulado = jsonarr.getJSONObject(i).getString("today_open_cases");
                    dto.total_fallecidos_acumulado = jsonarr.getJSONObject(i).getString("today_deaths");
                    dto.total_uci_acumulado = jsonarr.getJSONObject(i).getString("today_intensive_care");

                    dto.nuevos_casos = jsonarr.getJSONObject(i).getString("today_new_confirmed");
                    dto.nuevos_curados = jsonarr.getJSONObject(i).getString("today_new_recovered");
                    dto.nuevos_fallecidos = jsonarr.getJSONObject(i).getString("today_new_deaths");
                    dto.nuevos_uci = jsonarr.getJSONObject(i).getString("today_new_intensive_care");
                    dtoList.add(dto);
                    System.out.println(dto.toString());
                }
                cal.setTime(dateBefore7Days);
                cal.add(Calendar.DATE, 1);
                dateBefore7Days = cal.getTime();
                data_obj = copia;
            }

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("Rompe en el parser rango de fecha");
        }
        return dtoList;
    }
}
