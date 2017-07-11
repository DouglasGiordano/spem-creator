/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
@XStreamAlias("ContentElement")
public class ContentElement {

    @XStreamAsAttribute
    private String id;

    @XStreamAsAttribute
    @XStreamAlias("xsi:type")
    private String type;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String briefDescription;

    @XStreamAsAttribute
    private String presentationName;
    
    @XStreamImplicit(itemFieldName = "ResponsibleFor")
    private List<String> responsibleFor;
    
    @XStreamImplicit(itemFieldName = "PerformedBy")
    private List<String> performedBy;
    
    @XStreamImplicit(itemFieldName = "AdditionallyPerformedBy")
    private List<String> additionallyPerformedBy;

    @XStreamImplicit(itemFieldName = "MandatoryInput")
    private List<String> mandatoryInput;

    @XStreamImplicit(itemFieldName = "Output")
    private List<String> output;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the presentationName
     */
    public String getPresentationName() {
        return presentationName;
    }

    /**
     * @param presentationName the presentationName to set
     */
    public void setPresentationName(String presentationName) {
        this.presentationName = presentationName;
    }

    /**
     * @return the mandatoryInput
     */
    public List<String> getMandatoryInput() {
        return mandatoryInput;
    }

    /**
     * @param mandatoryInput the mandatoryInput to set
     */
    public void setMandatoryInput(List<String> mandatoryInput) {
        this.mandatoryInput = mandatoryInput;
    }

    /**
     * @return the output
     */
    public List<String> getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(List<String> output) {
        this.output = output;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the briefDescription
     */
    public String getBriefDescription() {
        return briefDescription;
    }

    /**
     * @param briefDescription the briefDescription to set
     */
    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    /**
     * @return the responsibleFor
     */
    public List<String> getResponsibleFor() {
        return responsibleFor;
    }

    /**
     * @param responsibleFor the responsibleFor to set
     */
    public void setResponsibleFor(List<String> responsibleFor) {
        this.responsibleFor = responsibleFor;
    }

    /**
     * @return the performedBy
     */
    public List<String> getPerformedBy() {
        return performedBy;
    }

    /**
     * @param performedBy the performedBy to set
     */
    public void setPerformedBy(List<String> performedBy) {
        this.performedBy = performedBy;
    }

    /**
     * @return the additionallyPerformedBy
     */
    public List<String> getAdditionallyPerformedBy() {
        return additionallyPerformedBy;
    }

    /**
     * @param additionallyPerformedBy the additionallyPerformedBy to set
     */
    public void setAdditionallyPerformedBy(List<String> additionallyPerformedBy) {
        this.additionallyPerformedBy = additionallyPerformedBy;
    }
}
