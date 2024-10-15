package com.java.home.assignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Probability {

    @JsonProperty("column")
    private int column;
    @JsonProperty("row")
    private int row;
    @JsonProperty("symbols")
    private Map<String, Integer> symbols;

    @JsonCreator
    public Probability(
            @JsonProperty("column") int column,
            @JsonProperty("row") int row,
            @JsonProperty("symbols") Map<String, Integer> symbols
    ) {
        this.column = column;
        this.row = row;
        this.symbols = symbols;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }
}
