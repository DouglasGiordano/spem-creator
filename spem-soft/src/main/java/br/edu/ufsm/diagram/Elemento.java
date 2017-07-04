/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.diagram;

import br.edu.ufsm.model.ContentElement;
import java.io.Serializable;
import java.util.Objects;
import org.primefaces.model.diagram.Element;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
public class Elemento implements Serializable {

    private String nome;
    private String descricao;
    private String pequenaDescricao;
    private Element elementoDiagrama;
    private ContentElement contentElement;

    public Elemento(String descricao, ContentElement contentElement) {
        this.descricao = descricao;
        this.contentElement = contentElement;
        this.nome = contentElement.getName();
        this.pequenaDescricao = contentElement.getBriefDescription();
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the elementoDiagrama
     */
    public Element getElementoDiagrama() {
        return elementoDiagrama;
    }

    /**
     * @param elementoDiagrama the elementoDiagrama to set
     */
    public void setElementoDiagrama(Element elementoDiagrama) {
        this.elementoDiagrama = elementoDiagrama;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elemento other = (Elemento) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the contentElement
     */
    public ContentElement getContentElement() {
        return contentElement;
    }

    /**
     * @param contentElement the contentElement to set
     */
    public void setContentElement(ContentElement contentElement) {
        this.contentElement = contentElement;
    }

    /**
     * @return the pequenaDescricao
     */
    public String getPequenaDescricao() {
        return pequenaDescricao;
    }

    /**
     * @param pequenaDescricao the pequenaDescricao to set
     */
    public void setPequenaDescricao(String pequenaDescricao) {
        this.pequenaDescricao = pequenaDescricao;
    }

}
