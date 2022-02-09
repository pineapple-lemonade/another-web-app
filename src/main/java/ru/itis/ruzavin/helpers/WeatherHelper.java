package ru.itis.ruzavin.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WeatherHelper {
	public static String getWeather(String city){
		URL getUrl = null;
		try {
			getUrl = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=d64cde12deca23990a6e956bf65b16aa");
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.setRequestMethod("GET");
			StringBuilder content;
			try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
				content = new StringBuilder();
				String input;
				while ((input = bufferedReader.readLine()) != null){
					content.append(input);
				}
			}
			connection.disconnect();
			return content.toString();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
