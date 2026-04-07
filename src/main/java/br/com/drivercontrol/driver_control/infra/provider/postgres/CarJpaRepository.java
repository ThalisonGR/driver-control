package br.com.drivercontrol.driver_control.infra.provider.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarJpaRepository extends JpaRepository<CarEntity, UUID> {
    Optional<CarEntity> findByPlate(String plate);
    Page<CarEntity> findAll(Pageable pageable);
}
