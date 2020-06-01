/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.entities;

import com.example.DLC2020.dal.commons.DalEntity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "DOCUMENTOS")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documentos.findAll", query = "SELECT d FROM Documentos d"),
    @NamedQuery(name = "Documentos.findByIddoc", query = "SELECT d FROM Documentos d WHERE d.iddoc = :iddoc"),
    @NamedQuery(name = "Documentos.findByNombre", query = "SELECT d FROM Documentos d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Documentos.findByUrl", query = "SELECT d FROM Documentos d WHERE d.url = :url")})
public class Documento implements Serializable, DalEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDOC")
    private Integer iddoc;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "URL")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentos")
    private Collection<Posteo> posteoCollection;

	public Documento() {
    }

    public Documento(Integer iddoc) {
        this.iddoc = iddoc;
    }

    public Documento(@NotNull Integer iddoc, @Size(max = 50) String nombre, @Size(max = 100) String url) {
		super();
		this.iddoc = iddoc;
		this.nombre = nombre;
		this.url = url;
	}

	public Integer getIddoc() {
        return iddoc;
    }

    public void setIddoc(Integer iddoc) {
        this.iddoc = iddoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @XmlTransient
    public Collection<Posteo> getPosteoCollection() {
		return posteoCollection;
	}

	public void setPosteoCollection(Collection<Posteo> posteoCollection) {
		this.posteoCollection = posteoCollection;
	}


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddoc != null ? iddoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.iddoc == null && other.iddoc != null) || (this.iddoc != null && !this.iddoc.equals(other.iddoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.DLC2020.entities.Documentos[ iddoc=" + iddoc + " ]";
    }

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Documento)) {
            return 0;
        }
        Documento other = (Documento) o;
		return Integer.compare(this.iddoc, other.iddoc);
	}
    
}
