package com.payroll.player2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    @JsonProperty("current_number")
    private int currentNumber;

    public Player() {
    }

    public Player(int number) {
        this.currentNumber = number;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }
}
