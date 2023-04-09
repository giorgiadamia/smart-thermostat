package com.example.smartthermostat.service;

import com.example.smartthermostat.model.domain.Thermostat;
import com.example.smartthermostat.model.domain.User;
import com.example.smartthermostat.repository.ThermostatRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ThermostatService {

    private final ThermostatRepository thermostatRepository;

    public ThermostatService(ThermostatRepository thermostatRepository) {
        this.thermostatRepository = thermostatRepository;
    }

    public void createThermostat(User user, Thermostat thermostat) {
        user.getThermostats().add(thermostat);
        thermostat.setUser(user);
        thermostatRepository.save(thermostat);
    }

    public List<Thermostat> showAll() {
        return thermostatRepository.findAll();
    }

    public Thermostat getThermostat(Long id) {
        return checkIfPresent(thermostatRepository.findById(id));
    }

    public void updateThreshold(Long id, Thermostat thermostat) {
        Thermostat thermostatFromMemory = checkIfPresent(thermostatRepository.findById(id));
        thermostatFromMemory.setThresholdTemperature(thermostat.getThresholdTemperature());
        if (thermostatFromMemory.getTemperature() != null) {
            thermostatFromMemory.setIsCritical(thermostatFromMemory.getTemperature()
                    > thermostatFromMemory.getThresholdTemperature());
        }
        thermostatRepository.save(thermostatFromMemory);
    }

    public void deleteThermostat(Long id) {
        checkIfPresent(thermostatRepository.findById(id));
        thermostatRepository.deleteById(id);
    }

    public void updateTemperature(Long id, Thermostat thermostat) {
        Thermostat thermostatFromMemory = checkIfPresent(thermostatRepository.findById(id));
        thermostatFromMemory.setTemperature(thermostat.getTemperature());
        thermostatFromMemory.setIsCritical(thermostatFromMemory.getTemperature() > thermostatFromMemory.getThresholdTemperature());
        thermostatRepository.save(thermostatFromMemory);
    }

    private static Thermostat checkIfPresent(Optional<Thermostat> thermostatOptional) {
        if (thermostatOptional.isEmpty()) {
            throw new EntityNotFoundException("Thermostat does not exist");
        }
        return thermostatOptional.get();
    }
}
