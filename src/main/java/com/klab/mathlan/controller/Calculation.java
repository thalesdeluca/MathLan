package com.klab.mathlan.controller;

import java.util.Random;

public class Calculation {
    private String operator;
    private String function;
    private int n1;
    private int n2;
    private int result;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String generateCalculation() {
        Random rand = new Random();

        int operator = rand.nextInt(2);
        int n1 = rand.nextInt(50);
        int n2 = rand.nextInt(50);

        setN1(n1);
        setN2(n2);

        if (operator == 0) {
            setOperator("+");
            setFunction(Integer.toString(n1) + " + " + Integer.toString(n2));
            setResult(n1 + n2);
        } else if (operator == 1) {
            setOperator("-");
            setFunction(Integer.toString(n1) + " - " + Integer.toString(n2));
            setResult(n1 - n2);
        } else {
            setOperator("*");
            setFunction(Integer.toString(n1) + " * " + Integer.toString(n2));
            setResult(n1 * n2);
        }

        return getFunction();
    }
}

