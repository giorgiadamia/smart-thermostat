package com.example.smartthermostat.model.dto;

import com.example.smartthermostat.model.Thermostat;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private List<Thermostat> thermostats;
}
