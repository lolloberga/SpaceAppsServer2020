package it.spaceapps.dto.otherinformation;

public class OtherInformation {
	
	private String name;
	private String URL;
	private String description;
	
	public OtherInformation(String name, String URL, String description) {
		super();
		this.name = name;
		this.URL = URL;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String URL) {
		this.URL = URL;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
