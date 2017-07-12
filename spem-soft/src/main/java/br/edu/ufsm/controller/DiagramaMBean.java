/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.controller;

import br.edu.ufsm.diagram.Diagrama;
import br.edu.ufsm.diagram.Elemento;
import br.edu.ufsm.diagram.Task;
import br.edu.ufsm.diagram.WorkProduct;
import br.edu.ufsm.parse.ParseXML;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.omnifaces.util.Faces;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
@Named("diagramBasicView")
@ViewScoped
public class DiagramaMBean implements Serializable {

    private Diagrama diagrama;
    private Elemento elementoAtual;
    private UploadedFile uploadedFile;
    private UploadedFile uploadedFileWorkProduct;

    @PostConstruct
    public void init() {
        DefaultDiagramModel model;
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        setDiagrama(new Diagrama(model));
    }

    public void novoDiagrama() {
        DefaultDiagramModel model;
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        setDiagrama(new Diagrama(model));
    }

    public void upload() throws UnsupportedEncodingException {
        byte[] bytes = uploadedFile.getContents();
        String str = new String(bytes, "UTF-8");
        System.out.println(str);
        getDiagrama().setMethodLibrary(ParseXML.parse(str));
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Upload completo",
                        "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
        getDiagrama().gerarDiagrama();
    }

    public void uploadWorkProduct() throws UnsupportedEncodingException {
        byte[] bytes = uploadedFileWorkProduct.getContents();
        File outputFile = new File(uploadedFileWorkProduct.getFileName());
        try (FileOutputStream outputStream = new FileOutputStream(outputFile);) {

            outputStream.write(bytes);  //write the bytes and your done. 

        } catch (Exception e) {
            e.printStackTrace();
        }
        ((WorkProduct) elementoAtual).setDocumento(outputFile);
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Upload completo",
                        "O arquivo " + uploadedFileWorkProduct.getFileName() + " foi salvo!"));
    }

    public void downloadDocumentoWorkProduct() {
        try {
            Faces.sendFile(((WorkProduct) elementoAtual).getDocumento(), true);
        } catch (IOException ex) {
        }
    }

    public void onElementClicked() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId").replace("diagrama-", "");
        Element element = getDiagrama().getModel().findElement(id);
        if (element == null) {
            return;
        }
        Elemento elemento = (Elemento) element.getData();
        elementoAtual = elemento;
    }

    public void finalizarActivity() {
        elementoAtual.getElementoDiagrama().setStyleClass("ui-diagram-element-atividade-finalizada");
        ((Task) elementoAtual).setFinalizado(true);
    }

    /**
     * @return the elementoAtual
     */
    public Elemento getElementoAtual() {

        System.out.println("elemento retornado " + elementoAtual);
        return elementoAtual;
    }

    /**
     * @param elementoAtual the elementoAtual to set
     */
    public void setElementoAtual(Elemento elementoAtual) {
        this.elementoAtual = elementoAtual;
        System.out.println("elemento adicionado " + elementoAtual);
    }

    /**
     * @return the uploadedFile
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile the uploadedFile to set
     */
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public boolean isTask() {
        if (elementoAtual instanceof Task) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWorkProduct() {
        if (elementoAtual instanceof WorkProduct) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the uploadedFileWorkProduct
     */
    public UploadedFile getUploadedFileWorkProduct() {
        return uploadedFileWorkProduct;
    }

    /**
     * @param uploadedFileWorkProduct the uploadedFileWorkProduct to set
     */
    public void setUploadedFileWorkProduct(UploadedFile uploadedFileWorkProduct) {
        this.uploadedFileWorkProduct = uploadedFileWorkProduct;
    }

    /**
     * @return the diagrama
     */
    public Diagrama getDiagrama() {
        return diagrama;
    }

    /**
     * @param diagrama the diagrama to set
     */
    public void setDiagrama(Diagrama diagrama) {
        this.diagrama = diagrama;
    }
}
