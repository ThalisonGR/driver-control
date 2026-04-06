package br.com.drivercontrol.driver_control.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository {

    void save(Car car);

    List<Car> findAll();

    Optional<Car> findById(UUID id);

    Optional<Car> findByPlaca(String placa);

    void delete(UUID id);
}