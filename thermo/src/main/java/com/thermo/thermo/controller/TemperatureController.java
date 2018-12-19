package com.thermo.thermo.controller;


import com.thermo.thermo.dtos.TemperatureDto;
import com.thermo.thermo.enums.SystemAction;
import com.thermo.thermo.models.Temperature;
import com.thermo.thermo.services.temperature.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @PostMapping("/add")
    public ResponseEntity<Temperature> newProduct(@RequestBody TemperatureDto temperatureDto) {
        Temperature temperature = temperatureService.addTemperature(temperatureDto);
        return new ResponseEntity<>(temperature, HttpStatus.OK);
    }

    @GetMapping("/getToday")
    public ResponseEntity<Temperature> getTodaysTemperature(){
        Temperature todaysTemp = temperatureService.getTodaysTemperature();
        return new ResponseEntity<>(todaysTemp, HttpStatus.OK);
    }

    @GetMapping("/predTomorrow")
    public ResponseEntity<Double> predTomorrowsTemperature(){
        Double tomorrowsTemp = temperatureService.predTomorrowsTemperature();
        return new ResponseEntity<>(tomorrowsTemp, HttpStatus.OK);
    }

    @GetMapping("/systemAction")
    public ResponseEntity<SystemAction> systemActionNeeded(){
        return new ResponseEntity<>(temperatureService.systemActionNeeded(), HttpStatus.OK);
    }
}
