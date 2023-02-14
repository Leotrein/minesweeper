package com.leot.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    // atributes
    private final Integer line;
    private final Integer column;

    private Boolean mined;
    private Boolean marked;
    private Boolean open;

    private List<Field> neighbors = new ArrayList<>();

    // constructors
    Field(Integer line, Integer column) {
        this.line = line;
        this.column = column;
    }

}
