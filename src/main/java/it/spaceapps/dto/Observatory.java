package it.spaceapps.dto;

import java.util.Date;

public class Observatory {
	private String id;
	private String name;
	private String start;
	private String end;
	
	public Observatory(String id, String name, String start, String end) {
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
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
	
	
}
