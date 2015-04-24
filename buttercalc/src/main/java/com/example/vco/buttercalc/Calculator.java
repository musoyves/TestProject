package com.example.vco.buttercalc;

import java.util.Observable;

/**
 * Created by cedric on 13-03-15.
 */
public class Calculator extends Observable {
    private double result;
    private String operator;
    private String input;

    public Calculator() {
        reset();
    }

    public void reset() {
        result = 0;
        operator = "";
        input = "";
        setChanged();
        notifyObservers("0");
    }

    public void undo() {
        input = input.substring(0, input.length() - 1);
        setChanged();
        notifyObservers(this.input);
    }

    public String  getResult() {
        compute();
        setChanged();
        notifyObservers(String.valueOf(result));
        return String.valueOf(result);
    }

    private void compute() {
        double i = Double.parseDouble(input);
        switch (operator) {
            case "+":
                this.result += i;
                break;
            case "-":
                this.result -= i;
                break;
            case "*":
                this.result *= i;
                break;
            case "/":
                try {
                    this.result /= i;
                }
                catch (ArithmeticException e){
                    setChanged();
                    notifyObservers(e.getMessage());
                    return;
                }
                break;
            default:
                this.result = i;
        }
        this.input = getResult();
        setChanged();
        notifyObservers(String.valueOf(result));
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        compute();
        this.operator = operator;
        if (!this.operator.equals("=")) this.input = ""; //reset opérande
        setChanged();
        notifyObservers(String.valueOf(this.result));
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input += input;
        if (this.input.startsWith("0")) this.input.substring(1);
        setChanged();
        notifyObservers(this.input);
    }
}
