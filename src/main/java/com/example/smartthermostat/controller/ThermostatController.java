package com.example.smartthermostat.controller;

import com.example.smartthermostat.model.domain.Thermostat;
import com.example.smartthermostat.model.domain.User;
import com.example.smartthermostat.service.ThermostatService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/thermostat")
public class ThermostatController {

    private final ThermostatService thermostatService;

    public ThermostatController(ThermostatService thermostatService) {
        this.thermostatService = thermostatService;
    }

    @PostMapping("/create")
    public boolean addThermostat(@AuthenticationPrincipal User user, @RequestBody Thermostat thermostat) {
        return thermostatService.createThermostat(user, thermostat);
    }
}
