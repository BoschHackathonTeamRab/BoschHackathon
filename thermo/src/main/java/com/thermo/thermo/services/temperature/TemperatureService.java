package com.thermo.thermo.services.temperature;

import com.thermo.thermo.dtos.TemperatureDto;
import com.thermo.thermo.enums.SystemAction;
import com.thermo.thermo.models.Temperature;

public interface TemperatureService {
    Temperature addTemperature(TemperatureDto temperatureDto);
    Temperature getTodaysTemperature();
    Double predTomorrowsTemperature();
    SystemAction systemActionNeeded();
}
