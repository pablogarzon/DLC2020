package com.example.DLC2020.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.DLC2020.dal.commons.DalEntity;

@Entity
@Table(name = "VOCABULARIO")
public class Vocabulario implements Serializable, DalEntity {

	private static final long serialVersionUID = 1L;
	
	@Id	
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 80)
	@Column(name = "PALABRA")
	private String palabra;
	
	@Column(name = "NR")
	private Integer nr;
	
	@Column(name = "MAXTF")
	private Integer maxtf;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vocabulario")
	private Collection<Posteo> posteos;

	public Vocabulario() {
		this.posteos = new ArrayList<Posteo>();
		this.nr = 0;
		this.maxtf = 0;
	}

	public Vocabulario(String palabra) {
		this();
		this.palabra = palabra;
	}

	public Vocabulario(String palabra, Collection<Posteo> posteos) {
		this();
		this.palabra = palabra;
		this.posteos = posteos;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public Integer getMaxtf() {
		return maxtf;
	}

	public void setMaxtf(Integer maxtf) {
		this.maxtf = maxtf;
	}

	public Collection<Posteo> getPosteos() {
		return posteos;
	}

	public void setPosteos(Collection<Posteo> posteos) {
		this.posteos = posteos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*
	 * description: agregar posteo a posteos y aumentar el cotador nr
	 */
	public void addPosteo(Posteo posteo) {
		if (this.posteos != null) {
			this.posteos.add(posteo);
			this.nr += 1;
		}
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (palabra != null ? palabra.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Vocabulario)) {
			return false;
		}
		Vocabulario other = (Vocabulario) object;
		if ((this.palabra == null && other.palabra != null)
				|| (this.palabra != null && !this.palabra.equals(other.palabra))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.example.DLC2020.entities.Vocabulario[ palabra=" + palabra + " ]";
	}

}
