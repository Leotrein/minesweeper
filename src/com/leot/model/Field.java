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

    // getters
    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    public Boolean getMined() {
        return mined;
    }

    public Boolean getMarked() {
        return marked;
    }

    public Boolean getOpen() {
        return open;
    }

    public List<Field> getNeighbors() {
        return neighbors;
    }

    // methods
    public Boolean addNeighbor(Field neighbor) {

        boolean lineDiff = this.getLine() != neighbor.line;
        boolean columnDiff = this.getColumn() != neighbor.column;
        boolean diagonal = lineDiff && columnDiff;

        int diff = Math.abs(this.getLine() - neighbor.line) + Math.abs(this.getColumn() - neighbor.column);

        if (diagonal && diff <= 2) {
            neighbors.add(neighbor);
            return true;
        } else if (!diagonal && diff == 1) {
            neighbors.add(neighbor);
            return true;
        }
        return false;
    }

}
