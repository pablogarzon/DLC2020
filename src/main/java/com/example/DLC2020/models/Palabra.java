package com.example.DLC2020.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "PALABRAS")
public class Palabra {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String palabra;
	
	public Palabra() {
		// TODO Auto-generated constructor stub
	}

	public Palabra(int id, String palabra) {
		super();
		this.id = id;
		this.palabra = palabra;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}
}
