package com.leot.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Integer line;
    private Integer column;
    private Integer mines;

    private final List<Field> fields = new ArrayList<>();

    public Board(Integer line, Integer column, Integer mines) {
        this.line = line;
        this.column = column;
        this.mines = mines;

        generateFields();
        associateNeighbors();
        spawnMines();
    }

    public Integer getLine() {
        return line;
    }

    private void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    private void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getMines() {
        return mines;
    }

    private void setMines(Integer mines) {
        this.mines = mines;
    }

    public List<Field> getFields() {
        return fields;
    }

    private void generateFields() {

    }

    private void associateNeighbors() {

    }

    private void spawnMines() {

    }

}
