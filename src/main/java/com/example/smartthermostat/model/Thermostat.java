package com.example.smartthermostat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Thermostat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "is_critical")
    private boolean isCritical;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
