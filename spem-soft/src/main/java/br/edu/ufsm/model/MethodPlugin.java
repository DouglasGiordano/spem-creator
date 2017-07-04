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
 * @author douglas
 */
@XStreamAlias("MethodPlugin")
public class MethodPlugin {

    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String authors;

    @XStreamImplicit(itemFieldName="MethodPackage")
    private List<MethodPackage> packageContent;
    
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
     * @return the authors
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * @return the packageContent
     */
    public List<MethodPackage> getPackageContent() {
        return packageContent;
    }

    /**
     * @param packageContent the packageContent to set
     */
    public void setPackageContent(List<MethodPackage> packageContent) {
        this.packageContent = packageContent;
    }
}
