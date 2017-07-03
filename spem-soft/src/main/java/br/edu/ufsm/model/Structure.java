/*
 */
package br.edu.ufsm.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
@XStreamAlias("structure")
public class Structure {
    private Automaton automaton;

    /**
     * @return the automaton
     */
    public Automaton getAutomaton() {
        return automaton;
    }

    /**
     * @param automaton the automaton to set
     */
    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
    }
}
