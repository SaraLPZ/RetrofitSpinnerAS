package com.example.retrofitspinneras.objets;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Municipio {
    @Element
    private String nm;

    public Municipio() {

    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    @Override
    public String toString() {
        return nm;
    }
}
