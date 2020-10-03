package it.spaceapps.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.spaceapps.adapter.Adapter;
import it.spaceapps.dto.Observatory;

@Component
public class ObservatoriesManager {
	
	@Value("${endpointSSC}")
	private String endpointSSC;
	
	public String getObservatories() {
		//Invoke GET request
		try {
			URL obj = new URL(endpointSSC + "/observatories");
			
			Document root = Adapter.getDOM(obj);
			NodeList nList = root.getElementsByTagName("Observatory");
			List<Observatory> result = new ArrayList<Observatory>();
			
//			SimpleDateFormat myDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			myDate.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			for(int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String id = eElement.getElementsByTagName("Id").item(0).getTextContent();
					String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
					String start = eElement.getElementsByTagName("StartTime").item(0).getTextContent();
					String end = eElement.getElementsByTagName("EndTime").item(0).getTextContent();
					
					result.add(new Observatory(id, name, start, end));
				}
			}
			
			return Adapter.getJson(result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}
		
		
		
		JSONObject jObj = new JSONObject();
		jObj.put("error", "OK");
		return jObj.toString();
	}
}
