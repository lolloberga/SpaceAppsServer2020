package it.spaceapps.manager;

import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.spaceapps.dto.Header;

@Component
public class GenericManager {

	public static final String MICROSERVICE = "thsigner";
	public static final String HEADER_LANG = "lang";
	public static final String HEADER_CUSTOMER = "customer";
	public static final String HEADER_ROLE = "roleID";
	public static final String HEADER_USER = "userID";
	public static final String HEADER_APPLICATION = "application";
	public final static String HEADER_AUTHTOKEN = "authToken";
	public final static String HEADER_AUTHORIZATION = "authorization";

	public static final String APPLICATION = "application";
	public static final String USERID = "userID";
	public static final String ROLEID = "roleID";
	public static final String CUSTOMER = "customer";
	public static final String LANG = "lang";
	public final static String AUTHTOKEN = "authToken";
	public final static String AUTHORIZATION = "authorization";
	public static final String SHOWALL = "showAll";
	public static final String SIMULATOR = "simulator";
	public static final String AGENT = "agent";

	public String setResponse(String message) {
		JSONObject jObj = new JSONObject();
		jObj.put("message", message);
		return jObj.toString();
	}

	public String setResponse(String message, Object obj) {
		Map<String, Object> oMap = new TreeMap<>();
		oMap.put("message", message);
		oMap.put("result", obj);
		return (new JSONObject(oMap)).toString();
	}

	public String setResponse(String message, Object[] obj) {
		Map<String, Object> oMap = new TreeMap<>();
		oMap.put("message", message);
		oMap.put("result", obj);
		return (new JSONObject(oMap)).toString();
	}

	public JSONObject isAlive(Header haeders, String service) {
		
		JSONObject jObj = new JSONObject();
		jObj.put("status", "OK");

		return jObj;
	}

}
