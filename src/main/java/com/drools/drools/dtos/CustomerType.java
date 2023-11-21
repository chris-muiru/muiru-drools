package com.drools.drools.dtos;

public enum CustomerType{
    LOYAL,
    NEW,
    DISSATISFIED;
    public String getValue() {
        return this.toString();
    }
}
