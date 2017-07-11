/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.diagram;

import br.edu.ufsm.model.ContentElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
public class Task extends Elemento {

    private boolean finalizado;
    private List<Elemento> entradas;
    private List<Elemento> saidas;
    private Elemento responsavel;
    private List<Elemento> outrosResponsaveis;

    public Task(String descricao, ContentElement contentElement) {
        super(descricao, contentElement);
        entradas = new ArrayList<>();
        saidas = new ArrayList<>();
        outrosResponsaveis = new ArrayList<>();
    }

    /**
     * @return the entradas
     */
    public List<Elemento> getEntradas() {
        return entradas;
    }

    /**
     * @param entradas the entradas to set
     */
    public void setEntradas(List<Elemento> entradas) {
        this.entradas = entradas;
    }

    /**
     * @return the saidas
     */
    public List<Elemento> getSaidas() {
        return saidas;
    }

    /**
     * @param saidas the saidas to set
     */
    public void setSaidas(List<Elemento> saidas) {
        this.saidas = saidas;
    }

    /**
     * @return the responsavel
     */
    public Elemento getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel the responsavel to set
     */
    public void setResponsavel(Elemento responsavel) {
        this.responsavel = responsavel;
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
     * @return the outrosResponsaveis
     */
    public List<Elemento> getOutrosResponsaveis() {
        return outrosResponsaveis;
    }

    /**
     * @param outrosResponsaveis the outrosResponsaveis to set
     */
    public void setOutrosResponsaveis(List<Elemento> outrosResponsaveis) {
        this.outrosResponsaveis = outrosResponsaveis;
    }
}
