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
@Table(name = "VOCABULARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vocabulario.findAll", query = "SELECT v FROM Vocabulario v"),
    @NamedQuery(name = "Vocabulario.findByPalabra", query = "SELECT v FROM Vocabulario v WHERE v.palabra = :palabra"),
    @NamedQuery(name = "Vocabulario.findByNr", query = "SELECT v FROM Vocabulario v WHERE v.nr = :nr"),
    @NamedQuery(name = "Vocabulario.findByMaxtf", query = "SELECT v FROM Vocabulario v WHERE v.maxtf = :maxtf")})
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
    private Collection<Posteo> posteoCollection;

    public Vocabulario() {
    }

    public Vocabulario(String palabra) {
        this.palabra = palabra;
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
        if ((this.palabra == null && other.palabra != null) || (this.palabra != null && !this.palabra.equals(other.palabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.DLC2020.entities.Vocabulario[ palabra=" + palabra + " ]";
    }
    
}
