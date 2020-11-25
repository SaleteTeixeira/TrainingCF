package com.accenture.trainingcf.dto;

import java.util.List;

public class SalesOrderDTO {

	private String id;
	private String status;
	private UsersDTO user;
	private ClientsDTO client;
	private List<SalesOrderItemDTO> items;
	private String createdAt;
	private String createdBy;
	private String modifiedAt;
	private String modifiedBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UsersDTO getUser() {
		return user;
	}
	public void setUser(UsersDTO user) {
		this.user = user;
	}
	public ClientsDTO getClient() {
		return client;
	}
	public void setClient(ClientsDTO client) {
		this.client = client;
	}
	public List<SalesOrderItemDTO> getItems() {
		return items;
	}
	public void setItems(List<SalesOrderItemDTO> items) {
		this.items = items;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
