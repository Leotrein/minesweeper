package com.leot.model;

import java.util.ArrayList;
import java.util.List;

import com.leot.exception.ExplosionException;

public class Field {

    private final Integer line;
    private final Integer column;

    private Boolean mined;
    private Boolean marked;
    private Boolean open;

    private List<Field> neighbors = new ArrayList<>();

    public Field(Integer line, Integer column) {
        this.line = line;
        this.column = column;
    }

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

    private void setMined(Boolean mined) {
        this.mined = mined;
    }

    private void setMarked(Boolean marked) {
        this.marked = marked;
    }

    private void setOpen(Boolean open) {
        this.open = open;
    }

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

    public void toggleMarkup() {

        if (!open) {
            setMarked(!marked);
        }

    }

    public Boolean openField() {

        if (!open && !getMarked()) {
            setOpen(true);
            if (mined) {
                throw new ExplosionException();
            } else if (isSafety()) {
                neighbors.forEach(n -> n.openField());
            }
            return true;
        }
        return false;
    }

    public Boolean isSafety() {
        return neighbors.stream().noneMatch(n -> n.mined);
    }

}
