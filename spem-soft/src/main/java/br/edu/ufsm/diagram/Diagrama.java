/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.diagram;

import br.edu.ufsm.model.ContentElement;
import br.edu.ufsm.model.MethodLibrary;
import java.util.ArrayList;
import java.util.List;
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
 * @author douglas giordano, eduardo bruning
 */
public class Diagrama {

    private DefaultDiagramModel model;
    private MethodLibrary methodLibrary;
    
    public Diagrama(DefaultDiagramModel model) {
        this.model=model;
    }

    private Connection criarConexao(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public void gerarDiagrama() {
        if (getMethodLibrary() == null) {
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
            adicionarElementosModelo(elemento, elementoD, getModel(), "ui-diagram-element-papeis");
            x += 20;
        }
        x = 0;
        y = 20;
        for (Elemento elemento : activities) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            adicionarElementosModelo(elemento, elementoD, getModel(), "ui-diagram-element-atividade");
            x += 20;
        }
        x = 0;
        y = 30;
        for (Elemento elemento : workProducts) {
            Element elementoD = new Element(elemento, x + "em", y + "em");
            adicionarElementosModelo(elemento, elementoD, getModel(), "ui-diagram-element-artefatos");
            x += 20;
        }
        for (Task elemento : activities) {
            for (Elemento entrada : elemento.getEntradas()) {
                getModel().connect(criarConexao(entrada.getElementoDiagrama().getEndPoints().get(0),
                        elemento.getElementoDiagrama().getEndPoints().get(0), "Entrada"));
            }
            for (Elemento saida : elemento.getSaidas()) {
                getModel().connect(criarConexao(elemento.getElementoDiagrama().getEndPoints().get(1),
                        saida.getElementoDiagrama().getEndPoints().get(1), "Saida"));
            }
            for (Elemento responsavel : elemento.getOutrosResponsaveis()) {
                getModel().connect(criarConexao(responsavel.getElementoDiagrama().getEndPoints().get(3),
                        elemento.getElementoDiagrama().getEndPoints().get(3), "Responsável"));
            }
            if (elemento.getResponsavel() != null) {
                getModel().connect(criarConexao(elemento.getResponsavel().getElementoDiagrama().getEndPoints().get(2),
                        elemento.getElementoDiagrama().getEndPoints().get(2), "Responsável"));

            }

        }
    }

    private void pesquisarElementos(List<Task> activities, List<Elemento> roles, List<WorkProduct> workProducts) {
        for (ContentElement elementoType : getMethodLibrary().getPlugin().getPackageContent().get(1).getContentElement()) {
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
                    for (String atividadeRole : tarefa.getContentElement().getAdditionallyPerformedBy()) {
                        if (role.getContentElement().getId().equalsIgnoreCase(atividadeRole)) {
                            tarefa.getOutrosResponsaveis().add(role);
                        }
                    }
                }
            }
        }

    }

    public void adicionarElementosModelo(Elemento elemento, Element elementoDiagrama, DiagramModel modelo, String styleClass) {
        elementoDiagrama.setStyleClass(styleClass);
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elementoDiagrama.addEndPoint(new BlankEndPoint(EndPointAnchor.AUTO_DEFAULT));
        elemento.setElementoDiagrama(elementoDiagrama);
        getModel().addElement(elementoDiagrama);
    }

    /**
     * @return the model
     */
    public DefaultDiagramModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    /**
     * @return the methodLibrary
     */
    public MethodLibrary getMethodLibrary() {
        return methodLibrary;
    }

    /**
     * @param methodLibrary the methodLibrary to set
     */
    public void setMethodLibrary(MethodLibrary methodLibrary) {
        this.methodLibrary = methodLibrary;
    }
}
