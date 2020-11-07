package es.uniovi.eii.minus_covid.util;

import android.content.Context;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

public class ApiConection {

	public static void ApiCall(String idComunidad) {
		try {
				URL url = new URL(
						"https://api.covid19tracking.narrativa.com/api/country/spain/region/" + idComunidad + "?date_from=2020-11-05&date_to=2020-11-06");

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
					JSONObject obj = (JSONObject) data_obj.get("dates");
					System.out.println(obj.toString());
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

	}

	public static void ApiCall() {
		ApiCall("Asturias");
	}
}
