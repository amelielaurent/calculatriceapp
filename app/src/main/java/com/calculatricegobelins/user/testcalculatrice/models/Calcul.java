package com.calculatricegobelins.user.testcalculatrice.models;

public class Calcul {

    private String entry;
    private double total;
    private double resultat;
    private String calculString;


    OperationType operationType = OperationType.UNKOWN;

    public Calcul(String entry) {
        this.total = 0.0;
        this.resultat = 0.0;
        this.calculString = "";
        this.entry = entry;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getResultat() {
        return resultat;
    }

    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

    public String getCalculString() {
        return calculString;
    }

    public void setCalculString(String calculString) {
        this.calculString = calculString;
    }

    public void doOperation() {


        String[] parts = entry.split(getSplitter());

        if (parts.length < 2) {
            System.out.println("fuck you");
        } else {

            double Number1 = Double.parseDouble(parts[0]);
            double Number2 = Double.parseDouble(parts[1]);

            Operation theOperation = new Operation(Number1, Number2, operationType);
            String resultFinal = String.format(theOperation.getResult().toString());

            System.out.println();
        }
    }


    /**
     * Gère les cas problématiques de regex avec les opérateurs
     *
     * @return
     */
    private String getSplitter() {
        switch (operationType) {
            case ADDTION:
                return "\\+";
            case DIVISION:
                return "\\/";
            case MUTIPLICATION:
                return "\\*";
            default:
                return operationType.toString();
        }
    }


}
