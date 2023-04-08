package com.example.smartthermostat.repository;

import com.example.smartthermostat.model.domain.Thermostat;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThermostatRepository extends CrudRepository<Thermostat, Long> {
    @NonNull List<Thermostat> findAll();
}
