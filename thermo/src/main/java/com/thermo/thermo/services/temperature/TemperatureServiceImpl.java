package com.thermo.thermo.services.temperature;

import com.thermo.thermo.dtos.TemperatureDto;
import com.thermo.thermo.enums.SystemAction;
import com.thermo.thermo.models.Temperature;
import com.thermo.thermo.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "temperatureService")
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Override
    public Temperature addTemperature(TemperatureDto temperatureDto) {
        Temperature temperature = new Temperature(temperatureDto.getDeviceID(), temperatureDto.getTemperatureValue(), temperatureDto.getDesireableTemperature());
        temperatureRepository.save(temperature);
        return temperature;
    }

    @Override
    public Temperature getTodaysTemperature() {
        List<Temperature> allTemps = temperatureRepository.findAll();
        return allTemps.get(allTemps.size() - 1);
    }

    @Override
    public Double predTomorrowsTemperature() {
        List<Temperature> allTemps = temperatureRepository.findAll();
        Double predTomTemp = (allTemps.get(allTemps.size() - 1).getTemperatureValue() + allTemps.get(allTemps.size() - 2).getTemperatureValue() + allTemps.get(allTemps.size() - 3).getTemperatureValue())/3;
        return predTomTemp;
    }

    @Override
    public SystemAction systemActionNeeded() {
        List<Temperature> allTemps = temperatureRepository.findAll();
        Temperature lastTemp = allTemps.get(allTemps.size() - 1);
        return lastTemp.getTemperatureValue() > lastTemp.getDesireableTemperature() ? SystemAction.COOLING : SystemAction.HEATING;
    }
}
