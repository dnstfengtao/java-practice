package com.dnst.gson;

/**
 * @author Finley
 */
public class Event {
    private String name;
    private String source;



    public Event(String name, String source) {
        this.name = name;
        this.source = source;
    }



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }



    /**
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }



    /**
     * @param source the source
     */
    public void setSource(String source) {
        this.source = source;
    }
}
