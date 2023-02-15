package com.leot.model;

import java.util.ArrayList;
import java.util.List;

import com.leot.exception.ExplosionException;

public class Field {

    private final Integer line;
    private final Integer column;

    private boolean mined;
    private boolean marked;
    private boolean open;

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

    public Boolean isMined() {
        return mined;
    }

    public Boolean isMarked() {
        return marked;
    }

    public Boolean isOpen() {
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

    public void undermine() {
        if (!isMined()) {
            setMined(true);
        }
    }

    public Boolean openField() {

        if (!open && !isMarked()) {
            setOpen(true);
            if (mined) {
                throw new ExplosionException();
            } else if (safety()) {
                neighbors.forEach(n -> n.openField());
            }
            return true;
        }
        return false;
    }

    public Boolean safety() {
        return neighbors.stream().noneMatch(n -> n.mined);
    }

}
