package org.example.parsetree;

public interface JSONElement {

    public String toString();

    //get value based on key provided
    public JValue getValue(String key);

}
