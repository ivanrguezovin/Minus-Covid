package es.uniovi.eii.minus_covid.util;

import org.json.*;

public class Parser {

    public static ComunidadDto parse(JSONObject obj){
        ComunidadDto dto = new ComunidadDto();
        try{
            JSONArray arr = obj.getJSONArray("posts");
            for (int i = 0; i < arr.length(); i++)
            {
                String post_id = arr.getJSONObject(i).getString("post_id");
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return dto;
    }
}
