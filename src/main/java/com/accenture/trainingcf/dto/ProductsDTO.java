package com.accenture.trainingcf.dto;

public class ProductsDTO {
	
	private String id;
	private String name;
	private String manufacturer;
	private Integer quantity;
	private Double salesPrice;
	private Double basePrice;
	private String validTo;
	private String validFrom;
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
	public String getValidTo(){
		return validTo;
	}
	public void setValidTo(String validTo){
		this.validTo = validTo;
	}
	public String getValidFrom(){
		return validFrom;
	}
	public void setValidFrom(String validFrom){
		this.validFrom = validFrom;
	}
	public String getCreatedAt(){
		return createdAt;
	}
	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedAt(){
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt){
		this.modifiedAt = modifiedAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}