package com.concretepage.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="assetdata")

@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset implements Serializable {
	
	private static final long serialVersionUID = 6L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ASSETID")
    private int id;
	
	@Column(name="asset_name")	
	private String assetName;	

	@Column(name="UID")
    private Integer userId;
	
	@Column(name="Cat_id")	
	private Integer categoryId;
	
	@Column(name="brand_desc")	
	private String brandDesc;
	
	@Column(name="brand_price")
    private Double brandPrice;	
	
	@Transient
    private byte[] image1;		

	@Transient
    private byte[] image2;	

	@Transient
    private byte[] image3;
	
	@Transient
    private byte[] invoiceImage;
	
	@Column(name="Image1Path")
    private String image1Path;
	
	@Column(name="Image2Path")
    private String image2Path;
	
	@Column(name="Image3Path")
    private String image3Path;
	
	@Column(name="Image4Path")
    private String invoicePath;
	
	@Column(name="AssetLocation")
    private String assetLocation;	
	
	@Column(name="age_of_asset")
    private Integer ageOfAsset;	
	
	@Column(name="quantity")
    private Integer quantity;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="purchase_date")
    private Date purchaseDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name="LAST_UPDATED")
    private Date lastUpdated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public Double getBrandPrice() {
		return brandPrice;
	}

	public void setBrandPrice(Double brandPrice) {
		this.brandPrice = brandPrice;
	}

	public String getImage1Path() {
		return image1Path;
	}

	public void setImage1Path(String image1Path) {
		this.image1Path = image1Path;
	}

	public String getImage2Path() {
		return image2Path;
	}

	public void setImage2Path(String image2Path) {
		this.image2Path = image2Path;
	}

	public String getImage3Path() {
		return image3Path;
	}

	public void setImage3Path(String image3Path) {
		this.image3Path = image3Path;
	}

	public String getInvoicePath() {
		return invoicePath;
	}

	public void setInvoicePath(String invoicePath) {
		this.invoicePath = invoicePath;
	}

	public String getAssetLocation() {
		return assetLocation;
	}

	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}

	public Integer getAgeOfAsset() {
		return ageOfAsset;
	}

	public void setAgeOfAsset(Integer ageOfAsset) {
		this.ageOfAsset = ageOfAsset;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}	

	public byte[] getInvoiceImage() {
		return invoiceImage;
	}

	public void setInvoiceImage(byte[] invoiceImage) {
		this.invoiceImage = invoiceImage;
	}
	
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Asset [id=" + id + ", assetName=" + assetName + ", userId="
				+ userId + ", categoryId=" + categoryId + ", brandDesc="
				+ brandDesc + ", brandPrice=" + brandPrice + ", image1Path="
				+ image1Path + ", image2Path=" + image2Path + ", image3Path="
				+ image3Path + ", invoicePath=" + invoicePath
				+ ", assetLocation=" + assetLocation + ", ageOfAsset="
				+ ageOfAsset + ", purchaseDate=" + purchaseDate
				+ ", lastUpdated=" + lastUpdated + "]";
	}
	
	
} 