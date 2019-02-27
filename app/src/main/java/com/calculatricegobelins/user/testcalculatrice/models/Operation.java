package com.calculatricegobelins.user.testcalculatrice.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe Operation
 * @author mickaeldebalme
 * @author robinsimonklein
 * @author amelielaurent
 * Effectue les opérations.
 */
public class Operation {

    private double number1, number2, result;
    private OperationType operationType;
    private Date theDate;

    /**
     * Constructor.
     * @param Number1 double
     * @param Number2 double
     * @param operationType OperationType
     */
    public Operation(double Number1, double Number2, OperationType operationType) {
        this.number1 = Number1;
        this.number2 = Number2;
        this.operationType = operationType;
        this.result = 0.0;
    }

    /**
     * Renvoie le résultat.
     * @return double
     */
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
            case INVERSE:
                return result = doInverse();
            default:
                return 0.0;
        }
    }

    /**
     * Effectue une addition.
     * @return double
     */
    private double doAddition() {
        return this.number1 + this.number2;
    }

    /**
     * Effectue une soustraction.
     * @return double
     */
    private double doSoustraction() {
        return this.number1 - this.number2;
    }

    /**
     * Effectue une division.
     * @return double
     */
    private double doDivision() {
        return this.number1 / this.number2;
    }

    /**
     * Effectue une multiplication.
     * @return double
     */
    private double doMultiplication() {
        return this.number1 * this.number2;
    }

    /**
     * Effectue un pourcentage.
     * @return double
     */
    private double doPercent() {
        return this.number1 / 100;
    }

    /**
     * Effectue l'inverse.
     * @return double
     */
    private double doInverse() {
        return this.number1 * (-1);
    }

}
