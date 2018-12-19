package com.thermo.thermo.dtos;

public class Value {
    private Unit unit;
    private InnerValue value;

    public Value() {

    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public InnerValue getValue() {
        return value;
    }

    public void setValue(InnerValue value) {
        this.value = value;
    }
}
