package com.example.DLC2020.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "DOCUMENTOS")
public class Documento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nombre;
	
	public Documento() {
		// TODO Auto-generated constructor stub
	}
	
	public Documento(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
