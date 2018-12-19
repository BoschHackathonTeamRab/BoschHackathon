package com.thermo.thermo;

import com.thermo.thermo.dtos.TemperatureDto;
import com.thermo.thermo.enums.DeviceID;
import com.thermo.thermo.enums.SystemAction;
import com.thermo.thermo.models.Temperature;
import com.thermo.thermo.repositories.TemperatureRepository;
import com.thermo.thermo.services.temperature.TemperatureServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThermoApplicationTests {

    @Mock
    TemperatureRepository mockRepository;

    @InjectMocks
    TemperatureServiceImpl service;

    @Test
    public void addTemperature_Should_AddTemperature_When_Add() {
        service.addTemperature(new TemperatureDto(20.0, 30.0));
        Assert.assertTrue(mockRepository.findAll().size() == 1);
    }

    @Test
    public void getTodaysTemperature_Should_ReturnTodaysTemperature_When_Called() {
        service.addTemperature(new TemperatureDto(20.0, 30.0));
        service.addTemperature(new TemperatureDto(30.0, 40.0));
        Assert.assertTrue(mockRepository.findAll().get(mockRepository.findAll().size() - 1).equals(service.getTodaysTemperature()));
    }

    @Test
    public void predTomorrowsTemperature_Should_PredictTemperature_When_Called() {
        service.addTemperature(new TemperatureDto(20.0, 30.0));
        service.addTemperature(new TemperatureDto(30.0, 40.0));
        service.addTemperature(new TemperatureDto(40.0, 50.0));
        Assert.assertTrue(service.predTomorrowsTemperature() == 40.0);
    }

    @Test
    public void systemActionNeeded_Should_ReturnHeating_When_DesireableTempIsHigherThanCurrent() {
        service.addTemperature(new TemperatureDto(20.0, 30.0));
        Assert.assertTrue(service.systemActionNeeded() == SystemAction.HEATING);
    }

    @Test
    public void systemActionNeeded_Should_ReturnCooling_When_DesireableTempIsLowerThanCurrent() {
        service.addTemperature(new TemperatureDto(20.0, 10.0));
        Assert.assertTrue(service.systemActionNeeded() == SystemAction.COOLING);
    }

}

