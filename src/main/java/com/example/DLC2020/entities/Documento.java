package com.example.DLC2020.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.example.DLC2020.dal.commons.DalEntity;

@Entity
@Table(name = "DOCUMENTOS")
public class Documento implements Serializable, DalEntity, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "IDDOC")
    private Integer iddoc;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 150)
    @Column(name = "URL")
    private String url;

	public Documento() {
    }

    public Documento(Integer iddoc) {
        this.iddoc = iddoc;
    }

    public Documento(Integer iddoc, @Size(max = 50) String nombre, @Size(max = 150) String url) {
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
