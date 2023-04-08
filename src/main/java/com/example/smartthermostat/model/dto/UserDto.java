package com.example.smartthermostat.model.dto;

import com.example.smartthermostat.model.domain.Thermostat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private List<Thermostat> thermostats;
}
