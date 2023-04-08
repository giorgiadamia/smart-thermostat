package com.example.smartthermostat.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Thermostat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "threshold_temperature")
    @NonNull
    private Integer thresholdTemperature;

    @Column(name = "is_critical")
    private boolean isCritical;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Thermostat{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", thresholdTemperature=" + thresholdTemperature +
                ", isCritical=" + isCritical +
                ", user=" + user.getId() +
                '}';
    }
}
