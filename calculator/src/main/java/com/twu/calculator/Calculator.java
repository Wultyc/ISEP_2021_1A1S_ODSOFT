package com.twu.calculator;

import java.util.stream.LongStream;

public class Calculator {

    private double accumulator;

    public double doOperation(String operation, double operand) {
        switch (operation) {
            case "add":
                accumulator += operand;
                break;
            case "subtract":
                accumulator -= operand;
                break;
            case "multiply":
                accumulator *= operand;
                break;
            case "divide":
                accumulator /= operand;
                break;
            case "double":
            	accumulator = accumulator * 2;
            	break;
            case "abs":
                accumulator = Math.abs(accumulator);
                break;
            case "neg":
                accumulator = -accumulator;
                break;
            case "sqrt":
                accumulator = Math.sqrt(accumulator);
                break;
            case "sqr":
                accumulator = Math.pow(accumulator, 2);
                break;
            case "cube":
                accumulator = Math.pow(accumulator, 3);
                break;
            case "cubert":
                accumulator = Math.cbrt(accumulator);
                break;
            case "third":
            	accumulator = accumulator/3;
            	break;
            case "exp":
                accumulator = Math.pow(accumulator, operand);
                break;
            case "cancel":
                accumulator = 0;
                break;
            case "factorial":
            	accumulator = LongStream.rangeClosed((long) 1, (long) operand).reduce(1, (long x, long y) -> x * y);
            	break;
            case "exit":
                System.exit(0);
        }
        return accumulator;
    }
}
