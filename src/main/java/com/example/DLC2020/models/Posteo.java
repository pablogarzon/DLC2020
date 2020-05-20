package com.example.DLC2020.models;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "POSTEOS")
public class Posteo {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Palabra palabra;
	
	@ElementCollection(fetch = FetchType.LAZY)
	private Map<Documento, Integer> documentos;
	
	public Posteo() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Palabra getPalabra() {
		return palabra;
	}

	public void setPalabra(Palabra palabra) {
		this.palabra = palabra;
	}

	public Map<Documento, Integer> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Map<Documento, Integer> documentos) {
		this.documentos = documentos;
	}
	
	public void addDocument(Documento documento) {
		documentos.put(documento, documentos.getOrDefault(documento, 0) + 1);
	}
}
