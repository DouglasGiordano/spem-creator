/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.controller;

import br.edu.ufsm.diagram.Elemento;
import br.edu.ufsm.diagram.Task;
import br.edu.ufsm.diagram.WorkProduct;
import br.edu.ufsm.model.ContentElement;
import br.edu.ufsm.model.MethodLibrary;
import br.edu.ufsm.parse.ParseXML;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;
import org.omnifaces.util.Faces;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
@Named("diagramBasicView")
@ViewScoped
public class DiagramaMBean implements Serializable {

    private DefaultDiagramModel model;
    private Elemento elementoAtual;
    private UploadedFile uploadedFile;
    private UploadedFile uploadedFileWorkProduct;
    private MethodLibrary methodLibrary;

    @PostConstruct
    public void init() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
//        FlowChartConnector connector = new FlowChartConnector();
//        connector.setAlwaysRespectStubs(true);
//        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
//        model.setDefaultConnector(connector);
    }

    public void novoDiagrama() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public DiagramModel getModel() {
        return model;
    }

    public void gerarDiagramaSpem() {
        if (methodLibrary == null) {
            return;
        }
        List<Task> activities = new ArrayList<Task>();
        List<WorkProduct> workProducts = new ArrayList<WorkProduct>();
        List<Elemento> roles = new ArrayList<Elemento>();
        pesquisarElementos(activities, roles, workProducts);
        ligarElementos(activities, roles, workProducts);
        int x = 0;
        int y = 5;
        for (Elemento elemento : roles) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            addElementModel(elemento, elementoD, model, "ui-diagram-element-papeis");
            x += 20;
        }
        x = 0;
        y = 20;
        for (Elemento elemento : activities) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            addElementModel(elemento, elementoD, model, "ui-diagram-element-atividade");
            x += 20;
        }
        x = 0;
        y = 30;
        for (Elemento elemento : workProducts) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            addElementModel(elemento, elementoD, model, "ui-diagram-element-artefatos");
            x += 20;
        }
        for (Task elemento : activities) {
            for (Elemento entrada : elemento.getEntradas()) {
                model.connect(createConnection(entrada.getElementoDiagrama().getEndPoints().get(0),
                        elemento.getElementoDiagrama().getEndPoints().get(0), "Entrada"));
            }
            for (Elemento saida : elemento.getSaidas()) {
                model.connect(createConnection(elemento.getElementoDiagrama().getEndPoints().get(1),
                        saida.getElementoDiagrama().getEndPoints().get(1), "Saida"));
            }
            for (Elemento responsavel : elemento.getOutrosResponsaveis()) {
                model.connect(createConnection(responsavel.getElementoDiagrama().getEndPoints().get(3),
                        elemento.getElementoDiagrama().getEndPoints().get(3), "Responsável"));
            }
            if (elemento.getResponsavel() != null) {
                model.connect(createConnection(elemento.getResponsavel().getElementoDiagrama().getEndPoints().get(2),
                        elemento.getElementoDiagrama().getEndPoints().get(2), "Responsável"));

            }

        }
    }

    private void pesquisarElementos(List<Task> activities, List<Elemento> roles, List<WorkProduct> workProducts) {
        for (ContentElement elementoType : methodLibrary.getPlugin().getPackageContent().get(1).getContentElement()) {
            switch (elementoType.getType()) {
                case "uma:Artifact":
                    workProducts.add(new WorkProduct(elementoType.getPresentationName(), elementoType));
                    break;
                case "uma:Task":
                    activities.add(new Task(elementoType.getPresentationName(), elementoType));
                    break;
                case "uma:Role":
                    roles.add(new Elemento(elementoType.getPresentationName(), elementoType));
                    break;
            }
        }
    }

    private void ligarElementos(List<Task> activities, List<Elemento> roles, List<WorkProduct> workProducts) {
        for (Task tarefa : activities) {
            for (WorkProduct workProduct : workProducts) {
                if (tarefa.getContentElement().getMandatoryInput() != null) {
                    for (String entrada : tarefa.getContentElement().getMandatoryInput()) {
                        if (entrada.equalsIgnoreCase(workProduct.getContentElement().getId())) {
                            tarefa.getEntradas().add(workProduct);
                        }
                    }
                }
                if (tarefa.getContentElement().getOutput() != null) {
                    for (String saida : tarefa.getContentElement().getOutput()) {
                        if (saida.equalsIgnoreCase(workProduct.getContentElement().getId())) {
                            tarefa.getSaidas().add(workProduct);
                        }
                    }
                }

            }

            for (Elemento role : roles) {
                if (tarefa.getContentElement().getPerformedBy() != null) {
                    for (String atividadeRole : tarefa.getContentElement().getPerformedBy()) {
                        if (role.getContentElement().getId().equalsIgnoreCase(atividadeRole)) {
                            tarefa.setResponsavel(role);
                        }
                    }
                }
                if (tarefa.getContentElement().getAdditionallyPerformedBy() != null) {
                    for (String atividadeRole : tarefa.getContentElement().getAdditionallyPerformedBy() ) {
                        if (role.getContentElement().getId().equalsIgnoreCase(atividadeRole)) {
                            tarefa.getOutrosResponsaveis().add(role);
                        }
                    }
                }
            }
        }

    }

    public void addElementModel(Elemento elemento, Element elementoDiagrama, DiagramModel modelo, String styleClass) {
        elementoDiagrama.setStyleClass(styleClass);
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elemento.setElementoDiagrama(elementoDiagrama);
        model.addElement(elementoDiagrama);
    }

    public void upload() throws UnsupportedEncodingException {
        byte[] bytes = uploadedFile.getContents();
        String str = new String(bytes, "UTF-8");
        System.out.println(str);
        methodLibrary = ParseXML.parse(str);
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Upload completo",
                        "O arquivo " + uploadedFile.getFileName() + " foi salvo!"));
        gerarDiagramaSpem();
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

    public void teste(Elemento el) {
        System.out.println(el);
    }

    public void teste2(String el) {
        System.out.println(el);
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

    public void onElementClicked() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("elementId").replace("diagrama-", "");
        Element element = model.findElement(id);
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
}
