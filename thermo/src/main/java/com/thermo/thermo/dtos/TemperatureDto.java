package com.thermo.thermo.dtos;


import com.thermo.thermo.enums.DeviceID;

public class TemperatureDto {
    private DeviceID deviceID;
    private Double temperatureValue;
    private Double desireableTemperature;

    public TemperatureDto() {
    }

    public TemperatureDto(Double temperatureValue, Double desireableTemperature) {
        this.temperatureValue = temperatureValue;
        this.desireableTemperature = desireableTemperature;
    }

    public Double getDesireableTemperature() {
        return desireableTemperature;
    }

    public void setDesireableTemperature(Double desireableTemperature) {
        this.desireableTemperature = desireableTemperature;
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
}
