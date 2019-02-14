import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class SendGet {

	static final String USER_AGENT = "Mozilla/5.0";

	public static String getData(String str) throws Exception {
		String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + str;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		// int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	public List<String> getMovieTitles(String substr) throws Exception {
		String tempStr = getData(substr);
		String data = tempStr.substring(tempStr.indexOf("["), tempStr.length() - 1);
		String preData = tempStr.substring(1, tempStr.indexOf("["));
		int totPgs = Integer.parseInt(preData.split(",")[3].split(":")[1].trim()); 
		ArrayList<String> list = new ArrayList<>();

		JSONArray arrayJSON = new JSONArray(data);
		for (int x = 0; x < arrayJSON.length(); x++) {
			JSONObject tmpJson = arrayJSON.getJSONObject(x);
			list.add(String.valueOf(tmpJson.get("Title")));
		}
		
		for (int i = 2; i <= totPgs; i++) {
			tempStr = getData(substr + "&page=" + i);
			data = tempStr.substring(tempStr.indexOf("["), tempStr.length() - 1);
			
			arrayJSON = new JSONArray(data);
			for (int x = 0; x < arrayJSON.length(); x++) {
				JSONObject tmpJson = arrayJSON.getJSONObject(x);
				list.add(String.valueOf(tmpJson.get("Title")));
			}
			
		}

		Collections.sort(list);

		return list;

	}
}
