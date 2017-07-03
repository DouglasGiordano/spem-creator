/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm;

import br.edu.ufsm.diagram.Elemento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
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
public class DiagramaMBean implements Serializable{

    private DefaultDiagramModel model;
    private Elemento elementoAtual;

    @PostConstruct
    public void init() {
        int x = 0;
        int y = 0;
        List<Elemento> activities = new ArrayList<Elemento>();
        List<Elemento> workProducts = new ArrayList<Elemento>();
        Elemento e1 = new Elemento("Levantar Requisitos");
        Elemento e2 = new Elemento("Rastrear Requisitos");
        Elemento e3 = new Elemento("Documentar os Requisitos");
        Elemento e4 = new Elemento("Caso de Uso");
        e1.getDestinos().add(e2);
        e1.getAgregacoes().add(e4);
        e2.getDestinos().add(e3);
        e2.getAgregacoes().add(e4);
        e3.getAgregacoes().add(e4);
        activities.add(e1);
        activities.add(e2);
        activities.add(e3);

        workProducts.add(e4);

        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

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
            y += 0;
        }
        x = 0;
        y = 20;
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
            y += 0;
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
        System.out.println("elemento adicionado "+elementoAtual);
    }
    
    public void teste(Elemento el){
        System.out.println(el);
    }
        public void teste2(String el){
        System.out.println(el);
    }
}
