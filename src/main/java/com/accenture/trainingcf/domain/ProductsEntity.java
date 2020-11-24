package com.accenture.trainingcf.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "\"TRAINING_PRODUCTS_TBLPRODUCTS\"")
public class ProductsEntity {
	
	@Id
	@Column(name = "\"ID\"")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "\"NAME\"")
	private String name;
	
	@Column(name = "\"MANUFACTURER\"")
	private String manufacturer;
	
	@Column(name = "\"QUANTITY\"")
	private Integer quantity;
	
	@Column(name = "\"SALESPRICE\"")
	private Double salesPrice;
	
	@Column(name = "\"BASEPRICE\"")
	private Double basePrice;
	
	@Column(name = "\"VALIDTO\"")
	private LocalDateTime validTo;
	
	@Column(name = "\"VALIDFROM\"")
	private LocalDateTime validFrom;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public Double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	public LocalDateTime getValidTo(){
		return validTo;
	}
	public void setValidTo(LocalDateTime validTo){
		this.validTo = validTo;
	}
	public LocalDateTime getValidFrom(){
		return validFrom;
	}
	public void setValidFrom(LocalDateTime validFrom){
		this.validFrom = validFrom;
	}
	public LocalDateTime getCreatedAt(){
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt){
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getModifiedAt(){
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt){
		this.modifiedAt = modifiedAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}