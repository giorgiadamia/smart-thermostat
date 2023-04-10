package com.example.smartthermostat.repository;

import com.example.smartthermostat.model.domain.Thermostat;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThermostatRepository extends CrudRepository<Thermostat, Long> {
    @NonNull List<Thermostat> findAll();

    @Modifying
    @Query(value = "DELETE FROM thermostats WHERE id = ?1" , nativeQuery = true)
    void deleteById(@NonNull Long id);
}
