package com.leot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.leot.exception.ExplosionException;

public class Board {

    private Integer line;
    private Integer column;
    private Integer mines;

    private final List<Field> fields = new ArrayList<>();

    public Board(Integer line, Integer column, Integer mines) {
        this.line = line;
        this.column = column;
        this.mines = mines;

        init();
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getMines() {
        return mines;
    }

    public List<Field> getFields() {
        return fields;
    }

    private void init() {
        generateFields();
        associateNeighbors();
        spawnMines();
    }

    public void restart() {
        fields.stream().forEach(f -> f.restart());
        spawnMines();
    }

    private void generateFields() {
        for (int i = 1; i <= line; i++) {
            for (int j = 1; j <= column; j++) {
                fields.add(new Field(i, j));
            }
        }
    }

    private void associateNeighbors() {
        for (Field f1 : fields) {
            for (Field f2 : fields) {
                f1.addNeighbor(f2);
            }
        }
    }

    private void spawnMines() {
        int mines = 0;
        do {
            Random random = new Random();
            int randomNum = random.nextInt(getColumn() * getLine());

            fields.get(randomNum).undermine();
            mines = (int) fields.stream().filter(f -> f.isMined()).count();
        } while (mines < getMines());
    }

    public void open(int line, int column) {
        try {
            fields.parallelStream()
                    .filter(f -> f.getColumn() == column && f.getLine() == line)
                    .findFirst()
                    .ifPresent(f -> f.openField());
        } catch (ExplosionException e) {
            fields.stream().forEach(f -> f.setOpen(true));
            throw e;
        }
    }

    public void toggleMarkup(int line, int column) {
        fields.parallelStream()
                .filter(f -> f.getColumn() == column && f.getLine() == line)
                .findFirst()
                .ifPresent(f -> f.toggleMarkup());
    }

    public boolean victory() {
        return fields.stream().allMatch(f -> f.completedGoal());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getColumn(); i++) {
            sb.append(" ");
            sb.append(i + 1);
            sb.append(" ");
        }
        sb.append("\n\n");

        int x = 0;
        for (int i = 0; i < getLine(); i++) {
            for (int j = 0; j < getColumn(); j++) {
                sb.append(" ");
                sb.append(fields.get(x));
                sb.append(" ");
                x++;
            }
            sb.append("  " + (i + 1) + "\n");
        }

        return sb.toString();
    }

}
