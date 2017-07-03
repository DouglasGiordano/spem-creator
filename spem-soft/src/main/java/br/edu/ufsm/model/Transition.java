/*
 */
package br.edu.ufsm.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author douglas giordano, eduardo bruning
 */
@XStreamAlias("transition")
public class Transition {
    private String from;
    private String to;
    private String read;

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the read
     */
    public String getRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(String read) {
        this.read = read;
    }
}
