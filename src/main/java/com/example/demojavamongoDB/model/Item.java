package com.example.demojavamongoDB.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Document(collection="itemLst")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Item(String name,int price,int count)
	{
		this.name=name;
		this.price=price;
		this.count=count;
	}
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private int price;
	
	@Getter
	@Setter
	private int count;
}
