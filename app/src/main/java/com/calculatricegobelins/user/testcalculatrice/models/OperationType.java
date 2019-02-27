package com.calculatricegobelins.user.testcalculatrice.models;

/**
 * Enumeration OperationType.
 * @author mickaeldebalme
 * @author robinsimonklein
 * @author amelielaurent
 */
public enum OperationType {
    ADDTION("+"),
    SOUSTRACTION("-"),
    MUTIPLICATION("*"),
    DIVISION("/"),
    PERCENT("%"),
    INVERSE("+/-"),
    UNKOWN("NA");

    private String value;

    /**
     * Constructor.
     * @param value string
     */
    OperationType(String value){
        this.value = value;
    }

    public String toString(){
        return value;
    }
}
