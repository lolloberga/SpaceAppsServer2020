package it.spaceapps.adapter;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Adapter {

	public static String getJson(Object o) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(o);
	}
	public static String getJsonHtmlEscaping(Object o) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        return gson.toJson(o);
    }
	
	@SuppressWarnings("rawtypes")
	public static Map getMapFromJson(String jsonString) throws JsonParseException, JsonMappingException, IOException{
		return new ObjectMapper().readValue(jsonString, HashMap.class);
	}
	
	@SuppressWarnings("rawtypes")
	public static Map getMapFromJson(Object o) throws JsonParseException, JsonMappingException, IOException{
		return getMapFromJson(getJson(o));
	}
	
	public static <T> T getObject(String jsonString, Class<T> clazz) {
		GsonBuilder builder = new GsonBuilder();
	    Gson gson = builder.create();
	    return gson.fromJson(jsonString, clazz);
	}
	
	public static Document getDOM(URL url) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(url.openStream());
		doc.getDocumentElement().normalize();
		
		return doc;
	}
}
