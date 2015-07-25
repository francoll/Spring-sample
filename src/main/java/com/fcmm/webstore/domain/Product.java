package com.fcmm.webstore.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.fcmm.webstore.validator.ProductId;

@XmlRootElement
public class Product {

	@Pattern(regexp="P[0-9]+", message="{Pattern.Product.productId.validation}")
	@ProductId
	private String productId;
	
	@Size(min=4, max=50, message="{Size.Product.name.validation}")
	private String name;
	
	@Min(value=0, message="Min.Product.unitPrice.validation}")
	@Digits(integer=8, fraction=2, message="{Digits.Product.unitPrice.validation}")
	@NotNull(message= "{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice;
	private String description;
	private String manufacturer;
	private String category;
	private long unitsInStock;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;
	@JsonIgnore
	private MultipartFile  productImage;

	public Product() {
		super();
	}

	public Product(String pProductId, String pName, BigDecimal pUnitPrice) {
		this.productId = pProductId;
		this.name = pName;
		this.unitPrice = pUnitPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String pProductId) {
		this.productId = pProductId;
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal pUnitPrice) {
		this.unitPrice = pUnitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String pManufacturer) {
		this.manufacturer = pManufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String pCategory) {
		this.category = pCategory;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long pUnitsInStock) {
		this.unitsInStock = pUnitsInStock;
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long pUnitsInOrder) {
		this.unitsInOrder = pUnitsInOrder;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean pDiscontinued) {
		this.discontinued = pDiscontinued;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String pCondition) {
		this.condition = pCondition;
	}
	
	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile pProductImage) {
		productImage = pProductImage;
	}

	@Override
	public boolean equals(Object pObj) {
		if (this == pObj)
			return true;
		if (pObj == null)
			return false;
		if (getClass() != pObj.getClass())
			return false;
		Product other = (Product) pObj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + "]";
	}

}
