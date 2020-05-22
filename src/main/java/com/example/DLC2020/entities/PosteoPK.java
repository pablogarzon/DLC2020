/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DLC2020.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Javier
 */
@Embeddable
public class PosteoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDOC")
    private int iddoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "PALABRA")
    private String palabra;

    public PosteoPK() {
    }

    public PosteoPK(int iddoc, String palabra) {
        this.iddoc = iddoc;
        this.palabra = palabra;
    }

    public int getIddoc() {
        return iddoc;
    }

    public void setIddoc(int iddoc) {
        this.iddoc = iddoc;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddoc;
        hash += (palabra != null ? palabra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosteoPK)) {
            return false;
        }
        PosteoPK other = (PosteoPK) object;
        if (this.iddoc != other.iddoc) {
            return false;
        }
        if ((this.palabra == null && other.palabra != null) || (this.palabra != null && !this.palabra.equals(other.palabra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.DLC2020.entities.PosteoPK[ iddoc=" + iddoc + ", palabra=" + palabra + " ]";
    }
    
}
