/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.diagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.primefaces.model.diagram.Element;

/**
 *
 * @author douglas
 */
public class Elemento implements Serializable{
    private String nome;
    private String descricao;
    private boolean finalizado;
    private List<Elemento> destinos;
    private List<Elemento> agregacoes;
    private Element elementoDiagrama;

    public Elemento(String descricao) {
        this.descricao = descricao;
        if (this.destinos == null) {
            this.destinos = new ArrayList<>();
        }
        if (this.agregacoes == null) {
            this.agregacoes = new ArrayList<>();
        }
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
     * @return the finalizado
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * @return the destinos
     */
    public List<Elemento> getDestinos() {
        return destinos;
    }

    /**
     * @param destinos the destinos to set
     */
    public void setDestinos(List<Elemento> destinos) {
        this.destinos = destinos;
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

    /**
     * @return the agregacoes
     */
    public List<Elemento> getAgregacoes() {

        return agregacoes;
    }

    /**
     * @param agregacoes the agregacoes to set
     */
    public void setAgregacoes(List<Elemento> agregacoes) {
        this.agregacoes = agregacoes;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.descricao);
        hash = 83 * hash + (this.finalizado ? 1 : 0);
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
        if (this.finalizado != other.finalizado) {
            return false;
        }
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
    
}
