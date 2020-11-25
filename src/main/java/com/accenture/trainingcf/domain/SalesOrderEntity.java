package com.accenture.trainingcf.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "\"TRAINING_SALESORDER_TBLSALESORDER\"")
public class SalesOrderEntity {

	@Id
	@Column(name = "\"ID\"")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "\"STATUS\"")
	private String status;
	
	@ManyToOne
    @JoinColumn(name = "\"CLIENT_ID\"")
    private ClientsEntity client;
	
	@ManyToOne
    @JoinColumn(name = "\"USER_ID\"")
    private UsersEntity user;
	
	@OneToMany(mappedBy = "salesOrder", cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH })
	private List<SalesOrderItemEntity> items;
	
	@Column(name = "\"CREATEDAT\"")
	private LocalDateTime createdAt;
	
	@Column(name = "\"CREATEDBY\"")
	private String createdBy;
	
	@Column(name = "\"MODIFIEDAT\"")
	private LocalDateTime modifiedAt;
	
	@Column(name = "\"MODIFIEDBY\"")
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
	public UsersEntity getUser() {
		return user;
	}
	public void setUser(UsersEntity user) {
		this.user = user;
	}
	public ClientsEntity getClient() {
		return client;
	}
	public void setClient(ClientsEntity client) {
		this.client = client;
	}
	public List<SalesOrderItemEntity> getItems(){
		return items;
	}
	public void setItems(List<SalesOrderItemEntity> items){
		this.items = items;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
