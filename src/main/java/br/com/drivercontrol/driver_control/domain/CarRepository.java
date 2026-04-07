package br.com.drivercontrol.driver_control.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository {

    void save(Car car);

    Page<Car> findAll(Pageable pageable);

    Optional<Car> findById(UUID id);

    Optional<Car> findByPlate(String plate);

    void delete(UUID id);
}