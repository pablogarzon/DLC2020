/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.entities;

import com.example.DLC2020.dal.commons.DalEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Javier
 */
@Entity
@Table(name = "POSTEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posteo.findAll", query = "SELECT p FROM Posteo p"),
    @NamedQuery(name = "Posteo.findByTf", query = "SELECT p FROM Posteo p WHERE p.tf = :tf"),
    @NamedQuery(name = "Posteo.findByIddoc", query = "SELECT p FROM Posteo p WHERE p.posteoPK.iddoc = :iddoc"),
    @NamedQuery(name = "Posteo.findByPalabra", query = "SELECT p FROM Posteo p WHERE p.posteoPK.palabra = :palabra")})
public class Posteo implements Serializable, DalEntity {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PosteoPK posteoPK;
    @Column(name = "TF")
    private Integer tf;
    @JoinColumn(name = "IDDOC", referencedColumnName = "IDDOC", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Documento documento;
    @JoinColumn(name = "PALABRA", referencedColumnName = "PALABRA", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vocabulario vocabulario;

    public Posteo() {
    }

    public Posteo(PosteoPK posteoPK) {
        this.posteoPK = posteoPK;
    }

    public Posteo(int iddoc, String palabra) {
        this.posteoPK = new PosteoPK(iddoc, palabra);
    }

    public PosteoPK getPosteoPK() {
        return posteoPK;
    }

    public void setPosteoPK(PosteoPK posteoPK) {
        this.posteoPK = posteoPK;
    }

    public Integer getTf() {
        return tf;
    }

    public void setTf(Integer tf) {
        this.tf = tf;
    }

    public Documento getDocumentos() {
        return documento;
    }

    public void setDocumentos(Documento documentos) {
        this.documento = documentos;
    }

    public Vocabulario getVocabulario() {
        return vocabulario;
    }

    public void setVocabulario(Vocabulario vocabulario) {
        this.vocabulario = vocabulario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posteoPK != null ? posteoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posteo)) {
            return false;
        }
        Posteo other = (Posteo) object;
        if ((this.posteoPK == null && other.posteoPK != null) || (this.posteoPK != null && !this.posteoPK.equals(other.posteoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.DLC2020.entities.Posteo[ posteoPK=" + posteoPK + " ]";
    }
    
}
