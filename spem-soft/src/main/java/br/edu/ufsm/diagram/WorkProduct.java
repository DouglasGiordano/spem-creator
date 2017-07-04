/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.diagram;

import br.edu.ufsm.model.ContentElement;
import java.io.File;

/**
 *
 * @author douglas
 */
public class WorkProduct extends Elemento{
    private File documento;
    public WorkProduct(String descricao, ContentElement contentElement) {
        super(descricao, contentElement);
    }

    /**
     * @return the documento
     */
    public File getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(File documento) {
        this.documento = documento;
    }
    
}
