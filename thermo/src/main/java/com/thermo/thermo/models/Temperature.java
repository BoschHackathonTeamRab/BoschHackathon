package com.thermo.thermo.models;

import com.thermo.thermo.enums.DeviceID;
import com.thermo.thermo.models.base.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "temperatures")
public class Temperature extends Model {
    private DeviceID deviceID;
    private Double temperatureValue;
    private Double desireableTemperature;

    protected Temperature(){}

    public Temperature(DeviceID deviceID, Double temperatureValue, Double desireableTemperature) {
        setDeviceID(deviceID);
        setTemperatureValue(temperatureValue);
        setDesireableTemperature(desireableTemperature);
    }

    public DeviceID getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(DeviceID deviceID) {
        this.deviceID = deviceID;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public Double getDesireableTemperature() {
        return desireableTemperature;
    }

    public void setDesireableTemperature(Double desireableTemperature) {
        this.desireableTemperature = desireableTemperature;
    }
}
