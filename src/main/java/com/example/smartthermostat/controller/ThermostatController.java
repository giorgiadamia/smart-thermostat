package com.example.smartthermostat.controller;

import com.example.smartthermostat.model.domain.Thermostat;
import com.example.smartthermostat.model.domain.User;
import com.example.smartthermostat.service.ThermostatService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thermostat")
public class ThermostatController {

    private final ThermostatService thermostatService;

    public ThermostatController(ThermostatService thermostatService) {
        this.thermostatService = thermostatService;
    }

    @PostMapping("/create")
    public void addThermostat(@AuthenticationPrincipal User user, @RequestBody Thermostat thermostat) {
        thermostatService.createThermostat(user, thermostat);
    }

    @GetMapping
    public List<Thermostat> showAllThermostats() {
        return thermostatService.showAll();
    }

    @GetMapping("/{id}")
    public Thermostat getThermostat(@PathVariable Long id) {
        return thermostatService.getThermostat(id);
    }

    @PutMapping("/threshold/{id}")
    public void updateThreshold(@PathVariable Long id, @RequestBody Thermostat thermostat) {
        thermostatService.updateThreshold(id, thermostat);
    }

    @PutMapping("/{id}")
    public void updateTemperature(@PathVariable Long id, @RequestBody Thermostat thermostat) {
        thermostatService.updateTemperature(id, thermostat);
    }

    @DeleteMapping("/{id}")
    public void deleteThermostat(@PathVariable Long id) {
        thermostatService.deleteThermostat(id);
    }
}
