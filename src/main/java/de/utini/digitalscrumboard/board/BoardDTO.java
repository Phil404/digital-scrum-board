package de.utini.digitalscrumboard.board;

import java.util.LinkedList;

public class BoardDTO {
    private Integer id;
    private LinkedList<Integer> rows;
    private LinkedList<Integer> columns;
    private String name;

    public BoardDTO(Integer id, LinkedList<Integer> rows, LinkedList<Integer> columns, String name) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkedList<Integer> getRows() {
        return rows;
    }

    public void setRows(LinkedList<Integer> rows) {
        this.rows = rows;
    }

    public LinkedList<Integer> getColumns() {
        return columns;
    }

    public void setColumns(LinkedList<Integer> columns) {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
