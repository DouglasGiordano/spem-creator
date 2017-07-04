/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 *
 * @author douglas
 */

@XStreamAlias("ContentElement")
public class ContentElement {

    @XStreamAsAttribute
    @XStreamAlias("xsi:type")
    private String type;
    
    @XStreamAsAttribute
    private String name;
    
    @XStreamAsAttribute
    private String presentationName;

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
}
