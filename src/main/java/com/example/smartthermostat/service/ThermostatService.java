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
        thermostat.setCritical(thermostat.getTemperature() > thermostat.getThresholdTemperature());
        thermostatRepository.save(thermostat);
    }

    public List<Thermostat> showAll() {
        return thermostatRepository.findAll();
    }

    public Thermostat getThermostat(Long id) {
        return checkIfPresent(thermostatRepository.findById(id));
    }

    public void updateThreshold(Long id, Integer threshold) {
        Thermostat thermostat = checkIfPresent(thermostatRepository.findById(id));
        thermostat.setThresholdTemperature(threshold);
        thermostat.setCritical(thermostat.getTemperature() > thermostat.getThresholdTemperature());
    }

    public void deleteThermostat(Long id) {
        checkIfPresent(thermostatRepository.findById(id));
        thermostatRepository.deleteById(id);
    }

    public void updateTemperature(Long id, Integer temperature) {
        Thermostat thermostat = checkIfPresent(thermostatRepository.findById(id));
        thermostat.setTemperature(temperature);
        thermostat.setCritical(thermostat.getTemperature() > thermostat.getThresholdTemperature());
    }

    private static Thermostat checkIfPresent(Optional<Thermostat> thermostatOptional) {
        if (thermostatOptional.isEmpty()) {
            throw new EntityNotFoundException("Thermostat does not exist");
        }
        return thermostatOptional.get();
    }
}
