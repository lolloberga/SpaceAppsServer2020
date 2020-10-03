package it.spaceapps.dto;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;

import it.spaceapps.manager.GenericManager;

public class Header {

	private String application;
	private String customer;
	private String roleID;
	private String userID;
	private String lang;
	private String authToken;
	private String authorization;
	private String showAll;
	private String simulator;
	private String agent;

	public Header(HttpHeaders headers) {
		this.application = headers.getRequestHeaders().getFirst(GenericManager.APPLICATION);
		this.userID = headers.getRequestHeaders().getFirst(GenericManager.USERID);
		this.roleID = headers.getRequestHeaders().getFirst(GenericManager.ROLEID);
		this.customer = headers.getRequestHeaders().getFirst(GenericManager.CUSTOMER);
		this.lang = headers.getRequestHeaders().getFirst(GenericManager.LANG);
		this.authToken = headers.getRequestHeaders().getFirst(GenericManager.AUTHTOKEN);
		this.authorization = headers.getRequestHeaders().getFirst(GenericManager.AUTHORIZATION);
		this.showAll = headers.getRequestHeaders().getFirst(GenericManager.SHOWALL);
		this.simulator = headers.getRequestHeaders().getFirst(GenericManager.SIMULATOR);
		this.agent = headers.getRequestHeaders().getFirst(GenericManager.AGENT);
	}

	public Header(ContainerRequestContext containerRequest) {
		this.application = containerRequest.getHeaderString(GenericManager.APPLICATION);
		this.userID = containerRequest.getHeaderString(GenericManager.USERID);
		this.roleID = containerRequest.getHeaderString(GenericManager.ROLEID);
		this.customer = containerRequest.getHeaderString(GenericManager.CUSTOMER);
		this.lang = containerRequest.getHeaderString(GenericManager.LANG);
		this.authToken = containerRequest.getHeaderString(GenericManager.AUTHTOKEN);
		this.authorization = containerRequest.getHeaderString(GenericManager.AUTHORIZATION);
		this.showAll = containerRequest.getHeaderString(GenericManager.SHOWALL);
		this.simulator = containerRequest.getHeaderString(GenericManager.SIMULATOR);
		this.agent = containerRequest.getHeaderString(GenericManager.AGENT);
	}

	public Header(HttpServletRequest request) {
		this.application = request.getHeader(GenericManager.HEADER_APPLICATION);
		this.userID = request.getHeader(GenericManager.HEADER_USER);
		this.roleID = request.getHeader(GenericManager.HEADER_ROLE);
		this.customer = request.getHeader(GenericManager.HEADER_CUSTOMER);
		this.lang = request.getHeader(GenericManager.HEADER_LANG);
		this.authToken = request.getHeader(GenericManager.HEADER_AUTHTOKEN);
		this.authorization = request.getHeader(GenericManager.HEADER_AUTHORIZATION);
		this.simulator = request.getHeader(GenericManager.SIMULATOR);
		this.agent = request.getHeader(GenericManager.AGENT);
	}

	public Header() {
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getShowAll() {
		return showAll;
	}

	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}

	public String getSimulator() {
		return simulator;
	}

	public void setSimulator(String simulator) {
		this.simulator = simulator;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}
}
