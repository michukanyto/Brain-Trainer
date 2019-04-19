package Model;

import java.util.Random;

public class MathOperation {
    private int number1;
    private int number2;
    private float result;
    private String [] operation = {"+","-","*","/"};

    Random random = new Random();
    

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public String[] getOperation() {
        return operation;
    }

    public void setOperation(String[] operation) {
        this.operation = operation;
    }
}
