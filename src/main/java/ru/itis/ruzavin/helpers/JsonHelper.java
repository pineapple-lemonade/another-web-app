package ru.itis.ruzavin.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonHelper {
	public Map<String, String> parseJson(String json) throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(json);

		int temp = (int) (jsonNode.get("main").get("temp").asDouble() - 273.15);

		map.put("description", jsonNode.get("weather").get(0).get("description").asText());
		map.put("temp", String.valueOf(temp));
		map.put("name", jsonNode.get("name").asText());

		return map;
	}
}
