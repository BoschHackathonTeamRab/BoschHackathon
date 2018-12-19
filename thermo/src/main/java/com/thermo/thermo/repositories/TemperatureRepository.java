package com.thermo.thermo.repositories;

import com.thermo.thermo.models.Temperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends CrudRepository<Temperature, Long> {
    List<Temperature> findAll();
}
