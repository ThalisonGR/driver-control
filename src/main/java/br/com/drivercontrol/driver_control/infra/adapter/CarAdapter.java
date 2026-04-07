package br.com.drivercontrol.driver_control.infra.adapter;

import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.infra.provider.postgres.CarJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CarAdapter implements CarRepository {

    private final CarJpaRepository jpaRepository;
    private final MapStructMapper mapper;

    @Override
    public void save(Car car) {
        var entity = mapper.toEntity(car);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Car> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Car> findByPlate(String plate) {
        return jpaRepository.findByPlate(plate).map(mapper::toDomain);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Page<Car> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(mapper::toDomain);
    }
}
