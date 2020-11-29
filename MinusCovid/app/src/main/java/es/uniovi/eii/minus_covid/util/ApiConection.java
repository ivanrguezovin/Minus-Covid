package es.uniovi.eii.minus_covid.util;

import android.os.AsyncTask;
import android.os.Build;
import android.text.format.DateUtils;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ApiConection extends AsyncTask<String, Integer, JSONObject> {

    static int option;
    static String region;
    public ApiConection(int option, String region){
        this.option=option;
        this.region=region;
    }

    public static JSONObject ApiCall() {
        JSONObject obj = null;
        try {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, -7);
            Date dateBefore7Days = cal.getTime();


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatedDate = sdf.format(date);
            String formateDateLastWeek = sdf.format(dateBefore7Days);
            String stringUrl = "";
            switch (option) {
                case 1:
                    stringUrl = "https://api.covid19tracking.narrativa.com/api/" + formatedDate + "/country/spain";
                    break;
                case 2:
                    stringUrl = "https://api.covid19tracking.narrativa.com/api/country/spain/region/" + region + "?date_from=" + formateDateLastWeek + "&date_to=" + formatedDate;
                    System.out.println(stringUrl);
                    break;
            }

            System.out.println(stringUrl);

            URL url = new URL(stringUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                // Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                // Close the scanner
                scanner.close();

                // Using the JSON simple library parse the string into a json object

                JSONObject data_obj = (JSONObject) new JSONTokener(inline).nextValue();

                // Get the required object from the above created object

                obj = (JSONObject) data_obj.get("dates");
                //obj = (JSONObject) obj.get(formatedDate);
                /*
                 * // Get the required data using its key
                 * System.out.println(obj.get("TotalRecovered"));
                 *
                 * JSONArray arr = (JSONArray) data_obj.get("Countries");
                 *
                 * for (int i = 0; i < arr.size(); i++) {
                 *
                 * JSONObject new_obj = (JSONObject) arr.get(i);
                 *
                 * if (new_obj.get("Slug").equals("albania")) {
                 * System.out.println("Total Recovered: " + new_obj.get("TotalRecovered"));
                 * break; } }
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        return ApiCall();
    }
}
