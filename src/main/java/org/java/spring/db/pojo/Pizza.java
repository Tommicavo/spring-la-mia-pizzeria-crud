package org.java.spring.db.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Entity
public class Pizza {
	
	// Properties
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true, length = 64)
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private String imageUrl;
	
	@Column(nullable = false)
	private int price;
	
	// Constructors
	public Pizza() {}
	public Pizza(String name, String description, String imageUrl, int price) {
		setName(name);
		setDescription(description);
		setImageUrl(imageUrl);
		setPrice(price);
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	// Methods
	public String getFormattedPrice() {
		double doublePrice = (getPrice() / 100.0);
		String formattedPrice = String.format("%.2f", doublePrice);
		return formattedPrice + "€";
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "] - " + getName() + " - " + getFormattedPrice();
	}	
}
