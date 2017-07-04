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

@XStreamAlias("uma:MethodLibrary")
public class MethodLibrary {

    @XStreamAsAttribute
    private String name;
    
    @XStreamAlias("MethodPlugin")
    private MethodPlugin plugin;

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
     * @return the plugin
     */
    public MethodPlugin getPlugin() {
        return plugin;
    }

    /**
     * @param plugin the plugin to set
     */
    public void setPlugin(MethodPlugin plugin) {
        this.plugin = plugin;
    }
}
