package models;

import enums.GateType;

public class Gate {
    int gateNumber;
    GateType gateType;

    public Gate(int gateNumber, GateType gateType) {
        this.gateNumber = gateNumber;
        this.gateType = gateType;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }
}
