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

    void setMined(Boolean mined) {
        this.mined = mined;
    }

    void setMarked(Boolean marked) {
        this.marked = marked;
    }

    void setOpen(Boolean open) {
        this.open = open;
    }

    public void restart() {
        setMarked(false);
        setMined(false);
        setOpen(false);
    }

    public Boolean addNeighbor(Field neighbor) {

        boolean lineDiff = this.getLine() != neighbor.getLine();
        boolean columnDiff = this.getColumn() != neighbor.getColumn();
        boolean diagonal = lineDiff && columnDiff;

        int diff = Math.abs(this.getLine() - neighbor.getLine()) + Math.abs(this.getColumn() - neighbor.getColumn());

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
        if (!isOpen()) {
            setMarked(!isMarked());
        }
    }

    public void undermine() {
        if (!isMined()) {
            setMined(true);
        }
    }

    public Boolean safety() {
        return neighbors.stream().noneMatch(n -> n.isMined());
    }

    public Boolean openField() {
        if (!open && !isMarked()) {
            setOpen(true);
            if (isMined()) {
                throw new ExplosionException();
            } else if (safety()) {
                neighbors.forEach(n -> n.openField());
            }
            return true;
        }
        return false;
    }

    public Boolean completedGoal() {
        boolean unraveled = !isMined() && isOpen();
        boolean safeField = isMarked() && isMined();

        if (unraveled || safeField) {
            return true;
        }
        return false;
    }

    public Long numberOfMines() {
        return neighbors.stream().filter(n -> n.isMined()).count();
    }

    public String toString() {
        if (isMarked()) {
            return "x";
        } else if (isOpen() && isMined()) {
            return "*";
        } else if (isOpen() && numberOfMines() > 0) {
            return Long.toString(numberOfMines());
        } else if (isOpen()) {
            return "-";
        }
        return "?";
    }

}
