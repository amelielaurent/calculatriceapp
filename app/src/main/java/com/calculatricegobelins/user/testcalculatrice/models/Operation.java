package com.calculatricegobelins.user.testcalculatrice.models;

import java.util.Calendar;
import java.util.Date;

/**
 *
 */

public class Operation {

    private double number1, number2, result;
    private OperationType operationType;
    private Date theDate;

    public Operation(double Number1, double Number2, OperationType operationType) {
        this.number1 = Number1;
        this.number2 = Number2;
        this.operationType = operationType;
        this.result = 0.0;
    }

    public Double getResult() {
        theDate = Calendar.getInstance().getTime();
        System.out.println(theDate);
        switch (operationType) {
            case ADDTION:
                return result = doAddition();
            case SOUSTRACTION:
                return result = doSoustraction();
            case DIVISION:
                return result = doDivision();
            case MUTIPLICATION:
                return result = doMultiplication();
            case PERCENT:
                return result = doPercent();
            default:
                return 0.0;
        }
    }

    private double doAddition() {
        return this.number1 + this.number2;
    }

    private double doSoustraction() {
        return this.number1 - this.number2;
    }

    private double doDivision() {
        return this.number1 / this.number2;
    }

    private double doMultiplication() {
        return this.number1 * this.number2;
    }

    private double doPercent() {
        return this.number1 / 100;
    }

}
