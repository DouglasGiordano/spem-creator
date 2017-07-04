/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm;

import br.edu.ufsm.diagram.Elemento;
import br.edu.ufsm.model.ContentElement;
import br.edu.ufsm.model.MethodLibrary;
import br.edu.ufsm.parse.ParseXML;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.FlowChartConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author douglas
 */
@ManagedBean(name = "diagramBasicView")
@ViewScoped
public class DiagramaMBean implements Serializable {

    private DefaultDiagramModel model;
    private Elemento elementoAtual;
    private UploadedFile uploadedFile;
    private MethodLibrary methodLibrary;

    @PostConstruct
    public void init() {

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        FlowChartConnector connector = new FlowChartConnector();
        connector.setAlwaysRespectStubs(true);
        connector.setPaintStyle("{strokeStyle:'#C7B097',lineWidth:3}");
        model.setDefaultConnector(connector);
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

        List<Elemento> activities = new ArrayList<Elemento>();
        List<Elemento> workProducts = new ArrayList<Elemento>();
        List<Elemento> roles = new ArrayList<Elemento>();
        for (ContentElement elementoType : methodLibrary.getPlugin().getPackageContent().get(1).getContentElement()) {
            switch (elementoType.getType()) {
                case "uma:Artifact":
                    workProducts.add(new Elemento(elementoType.getPresentationName()));
                    break;
                case "uma:Task":
                    activities.add(new Elemento(elementoType.getPresentationName()));
                    break;
                case "uma:Role":
                    roles.add(new Elemento(elementoType.getPresentationName()));
                    break;
            }

        }
        int x = 0;
        int y = 5;

        for (Elemento elemento : roles) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            elementoD.setStyleClass("ui-diagram-element-papeis");
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elemento.setElementoDiagrama(elementoD);
            model.addElement(elementoD);
            x += 20;
        }

        x = 0;
        y = 20;
        for (Elemento elemento : activities) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
//            elementoD.setStyleClass(styleClass);
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elemento.setElementoDiagrama(elementoD);
            model.addElement(elementoD);
            x += 20;
        }
        x = 0;
        y = 30;
        for (Elemento elemento : workProducts) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            elementoD.setStyleClass("ui-diagram-element-artefatos");
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elementoD.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
            elemento.setElementoDiagrama(elementoD);
            model.addElement(elementoD);
            x += 20;
        }

        for (Elemento elemento : activities) {
            for (int i = 0; i < elemento.getDestinos().size(); i++) {
                model.connect(createConnection(elemento.getElementoDiagrama().getEndPoints().get(0), elemento.getDestinos().get(i).getElementoDiagrama().getEndPoints().get(1), null));
            }
            for (int j = 0; j < elemento.getAgregacoes().size(); j++) {
                model.connect(createConnection(elemento.getElementoDiagrama().getEndPoints().get(2), elemento.getAgregacoes().get(j).getElementoDiagrama().getEndPoints().get(3), null));
            }
        }
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
}
