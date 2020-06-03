/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.DLC2020.dal.commons.DalEntity;

@Entity
@Table(name = "POSTEO")
public class Posteo implements Serializable, DalEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "IDPOSTEO")
	private long id;

	@JoinColumn(name = "IDDOC", referencedColumnName = "IDDOC")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Documento documento;
	
	@JoinColumn(name = "PALABRA", referencedColumnName = "PALABRA")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Vocabulario vocabulario;
    
    @Column(name = "TF")
    private Integer tf;

	public Posteo() {

	}
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Documento getDocumento() {
		return documento;
	}



	public void setDocumento(Documento documento) {
		this.documento = documento;
	}



	public Vocabulario getVocabulario() {
		return vocabulario;
	}



	public void setVocabulario(Vocabulario vocabulario) {
		this.vocabulario = vocabulario;
	}



	public Integer getTf() {
		return tf;
	}



	public void setTf(Integer tf) {
		this.tf = tf;
	}
	
	public void incrementTf(Integer tf) {
		this.tf += tf;
	}



	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.documento.getIddoc() != null ? this.documento.getIddoc().hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Posteo)) {
			return false;
		}
		Posteo other = (Posteo) object;
		if ((this.documento == null && other.documento != null)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.DLC2020.entities.Posteo[ doc=" + this.documento.getIddoc() + ", palabra = " + this.vocabulario.getPalabra() + " ]";
	}

}
