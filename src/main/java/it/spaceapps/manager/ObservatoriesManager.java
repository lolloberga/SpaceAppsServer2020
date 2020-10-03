package it.spaceapps.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.catalina.Server;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.spaceapps.adapter.Adapter;
import it.spaceapps.dto.Observatory;
import it.spaceapps.dto.otherinformation.OtherInformation;

@Component
public class ObservatoriesManager {
	
	@Value("${endpointSSC}")
	private String endpointSSC;
	
	@Value("${endpointResource}")
	private String endpointResource;
	
	public ResponseEntity<String> getObservatories() {
		//Invoke GET request
		try {
			URL obj = new URL(endpointSSC + "/observatories");
			
			Document root = Adapter.getDOM(obj);
			NodeList nList = root.getElementsByTagName("Observatory");
			List<Observatory> result = new ArrayList<Observatory>();
			
			//			SimpleDateFormat myDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			//			myDate.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			//Create model list of observatory
			
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					String id = eElement.getElementsByTagName("Id").item(0).getTextContent();
					String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
					String start = eElement.getElementsByTagName("StartTime").item(0).getTextContent();
					String end = eElement.getElementsByTagName("EndTime").item(0).getTextContent();
					String resourceId = eElement.getElementsByTagName("ResourceId").item(0).getTextContent();
					
					if(resourceId.contains("spase://")) {
						resourceId = resourceId.replaceAll("spase://", "");
					}
					
					result.add(new Observatory(id, name, start, end, resourceId, null));
				}
			}
			
			return new ResponseEntity<String>(Adapter.getJson(result), HttpStatus.OK);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (SAXException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (DOMException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<String> getPopularObservatories()
	{         
        try
        {
        	InputStream _stream = getClass().getResourceAsStream("popular.json");
        	
        	InputStreamReader _reader = new InputStreamReader(_stream);
        	
        	BufferedReader _br = new BufferedReader(_reader);
        	
        	String s = _br.readLine();
        	
        	String json = "";
        	
        	while(s != null) {
        		
        		json += s;
        		
        		s = _br.readLine();
        	}
        	
        	_br.close();
        	
			return new ResponseEntity<String>(json, HttpStatus.OK);
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
        }
	}

	public ResponseEntity<String> getDetail(String resourceId)
	{         
		try {
			
			String uri = endpointResource + resourceId + ".json";
			
			HttpClient client = HttpClient.newHttpClient();
		    HttpRequest request = HttpRequest.newBuilder()
		          .uri(URI.create(uri))
		          .build();

		    HttpResponse<String> response =
		          client.send(request, BodyHandlers.ofString());

		    String responseBody = response.body();
		    
		    JSONObject _obj = new JSONObject(responseBody).getJSONObject("Spase").getJSONObject("Observatory").getJSONObject("ResourceHeader");
		    
		    Observatory _obs = new Observatory();
		    
		    _obs.setDescription( _obj.getString("Description"));
		    
		    List<OtherInformation> _list = new ArrayList<OtherInformation>();
		    
		    JSONArray _objOtherInformation = _obj.getJSONArray("InformationURL");
		    
		    for(int i = 0; i < _objOtherInformation.length(); i++) {
		    	
		    	JSONObject _json = _objOtherInformation.getJSONObject(i);
		    	
		    	String name = _json.getString("Name");
		    	String URL = _json.getString("URL");
		    	String description = _json.getString("Description");
		    	
		    	_list.add(new OtherInformation(name, URL, description));
		    	
		    }
		    
		    _obs.setInformationUrl(_list);
			
			return new ResponseEntity<String>(Adapter.getJson(_obs), HttpStatus.OK);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (DOMException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		} catch (InterruptedException e) {
			e.printStackTrace();
			JSONObject jObj = new JSONObject();
			jObj.put("error", e.getMessage());
			return new ResponseEntity<String>(Adapter.getJson(jObj), HttpStatus.BAD_REQUEST);
		}
	}
}
