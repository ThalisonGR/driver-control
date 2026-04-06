package br.com.drivercontrol.driver_control.infra.provider.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarJpaRepository extends JpaRepository<CarEntity, UUID> {
    Optional<CarEntity> findByPlaca(String placa);
}
