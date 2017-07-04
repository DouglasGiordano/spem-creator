/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufsm.model;

import br.edu.ufsm.converter.MethodPackegeConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;

/**
 *
 * @author douglas
 */
@XStreamAlias("MethodPackage")
@XStreamConverter(MethodPackegeConverter.class)
public class MethodPackage {

    @XStreamAsAttribute
    @XStreamAlias("xsi:type")
    private String type;

    @XStreamImplicit(itemFieldName = "ContentElement")
    private List<ContentElement> contentElement;

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
     * @return the contentElement
     */
    public List<ContentElement> getContentElement() {
        return contentElement;
    }

    /**
     * @param contentElement the contentElement to set
     */
    public void setContentElement(List<ContentElement> contentElement) {
        this.contentElement = contentElement;
    }
}
