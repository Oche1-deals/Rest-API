package com.oche.rest.webservices.restful_web_service.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String filed1;

    private String filed2;
    //@JsonIgnore
    private String filed3;

    public SomeBean(String filed1, String filed2, String filed3) {
        this.filed1 = filed1;
        this.filed2 = filed2;
        this.filed3 = filed3;
    }

    public String getFiled1() {
        return filed1;
    }

    public String getFiled2() {
        return filed2;
    }

    public String getFiled3() {
        return filed3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "filed1='" + filed1 + '\'' +
                ", filed2='" + filed2 + '\'' +
                ", filed3='" + filed3 + '\'' +
                '}';
    }
}
