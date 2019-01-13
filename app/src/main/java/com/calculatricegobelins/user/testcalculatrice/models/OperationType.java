package com.calculatricegobelins.user.testcalculatrice.models;

public enum OperationType {
    ADDTION("+"),
    SOUSTRACTION("-"),
    MUTIPLICATION("*"),
    DIVISION("/"),
    PERCENT("%"),
    INVERSE("+/-"),
    UNKOWN("NA");

    private String value;

    //Constructeur
    OperationType(String value){
        this.value = value;
    }

    public String toString(){
        return value;
    }
}
