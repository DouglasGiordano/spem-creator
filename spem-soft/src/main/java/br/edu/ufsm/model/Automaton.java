/*
 */
package br.edu.ufsm.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
@XStreamAlias("automaton")
public class Automaton {

    @XStreamImplicit(itemFieldName = "state")
    private List<State> states = new ArrayList<State>();
    @XStreamImplicit(itemFieldName = "transition")
    private List<Transition> transitions = new ArrayList<Transition>();

    public Automaton(){
        
    }
    
    /**
     * @return the states
     */
    public List<State> getStates() {
        return states;
    }

    /**
     * @param states the states to set
     */
    public void setStates(List<State> states) {
        this.states = states;
    }

    /**
     * @return the transitions
     */
    public List<Transition> getTransitions() {
        return transitions;
    }

    /**
     * @param transitions the transitions to set
     */
    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
