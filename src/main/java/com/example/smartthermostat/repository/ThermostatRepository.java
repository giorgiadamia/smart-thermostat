package com.example.smartthermostat.repository;

import com.example.smartthermostat.model.domain.Thermostat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThermostatRepository extends CrudRepository<Thermostat, Long> {
}
