package es.uniovi.eii.minus_covid.util;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<ComunidadDto> parse(JSONObject data_obj) {
        ArrayList<ComunidadDto> dtoList = new ArrayList<ComunidadDto>();
        try {
            data_obj = (JSONObject) data_obj.get("countries");
            data_obj = (JSONObject) data_obj.get("Spain");
            JSONArray jsonarr = data_obj.getJSONArray("regions");
            for (int i = 0; i < jsonarr.length(); i++) {
                ComunidadDto dto = new ComunidadDto();
                dto.id = jsonarr.getJSONObject(i).getString("id");
                dto.nombre = jsonarr.getJSONObject(i).getString("name_es");

                dto.total_casos_acumulado = jsonarr.getJSONObject(i).getInt("today_confirmed");
                dto.total_curados_acumulado = jsonarr.getJSONObject(i).getInt("today_recovered");
                dto.total_infectados_acumulado = jsonarr.getJSONObject(i).getInt("today_open_cases");
                dto.total_fallecidos_acumulado = jsonarr.getJSONObject(i).getInt("today_deaths");
                dto.total_uci_acumulado = jsonarr.getJSONObject(i).getInt("today_intensive_care");

                dto.nuevos_casos = jsonarr.getJSONObject(i).getInt("today_new_confirmed");
                dto.nuevos_curados = jsonarr.getJSONObject(i).getInt("today_new_recovered");
                dto.nuevos_fallecidos = jsonarr.getJSONObject(i).getInt("today_new_deaths");
                dto.nuevos_uci = jsonarr.getJSONObject(i).getInt("today_new_intensive_care");
                dtoList.add(dto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dtoList;
    }
}
