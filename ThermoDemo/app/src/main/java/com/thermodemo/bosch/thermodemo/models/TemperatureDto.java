package com.thermodemo.bosch.thermodemo.models;

public class TemperatureDto {
    private DeviceID deviceID;
    private Double temperatureValue;
    private Double desireableTemperature;

    public TemperatureDto() {
    }

    public TemperatureDto(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
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
