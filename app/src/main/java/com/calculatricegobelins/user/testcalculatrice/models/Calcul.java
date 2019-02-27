package com.calculatricegobelins.user.testcalculatrice.models;

/**
 * Classe Calcul
 * @author mickaeldebalme
 * @author robinsimonklein
 * @author amelielaurent
 * Effectue les opérations de calcul.
 */
public class Calcul {

    private String entry;
    private double total;
    private double resultat;
    private String calculString;


    private OperationType operationType = OperationType.UNKOWN;

    /**
     * Constructor.
     * @param entry String
     */
    public Calcul(String entry) {
        this.total = 0.0;
        this.resultat = 0.0;
        this.calculString = "";
        this.entry = entry;
    }

    /**
     * Récupère le total du calcul.
     * @return double
     */
    public double getTotal() {
        return total;
    }

    /**
     * Définit le total du calcul.
     * @param total double
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Récupère le résultat du calcul.
     * @return double
     */
    public double getResultat() {
        return resultat;
    }

    /**
     * Définit le résultat du calcul.
     * @param resultat double
     */
    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

    /**
     * Récupère la chaîne de caractères détaillant le calcul.
     * @return string
     */
    public String getCalculString() {
        return calculString;
    }

    /**
     * Définit la chaîne de caractères détaillant le calcul.
     * @param calculString string
     */
    public void setCalculString(String calculString) {
        this.calculString = calculString;
    }

    /**
     * Effectue une opération.
     */
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
     * @return string
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
