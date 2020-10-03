package it.spaceapps.dto;

import java.util.List;

import it.spaceapps.dto.otherinformation.OtherInformation;

public class Observatory {
	private String id; //id
	private String name; //name of observatory
	private String start; //start service
	private String end; //time limit to get position / end service
	private String description; //description of observatory
	private String url; //image url of observatory
	private String resourceId; //resourceId for extra detail
	private List<OtherInformation> Details; //extra details
	
	public Observatory() {
		
	}
	
	public Observatory(String id, String name, String start, String end, String resourceId, String description) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.resourceId = resourceId;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public void setEnd(String end) {
		this.end = end;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<OtherInformation> getInformationUrl() {
		return Details;
	}

	public void setInformationUrl(List<OtherInformation> informationUrl) {
		Details = informationUrl;
	}
	
	
}
