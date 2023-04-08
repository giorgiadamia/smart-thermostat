package com.example.smartthermostat.service;

import com.example.smartthermostat.model.domain.Thermostat;
import com.example.smartthermostat.model.domain.User;
import com.example.smartthermostat.repository.ThermostatRepository;
import org.springframework.stereotype.Service;

@Service
public class ThermostatService {

    private final ThermostatRepository thermostatRepository;

    public ThermostatService(ThermostatRepository thermostatRepository) {
        this.thermostatRepository = thermostatRepository;
    }

    public boolean createThermostat(User user, Thermostat thermostat) {
        user.getThermostats().add(thermostat);
        thermostat.setUser(user);
        thermostat.setCritical(thermostat.getTemperature() > thermostat.getThresholdTemperature());
        thermostatRepository.save(thermostat);
        return true;
    }
}
