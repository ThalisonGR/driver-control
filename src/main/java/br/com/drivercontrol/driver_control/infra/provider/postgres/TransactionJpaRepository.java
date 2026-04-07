package br.com.drivercontrol.driver_control.infra.provider.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {
    Page<TransactionEntity> findByCarId(UUID carId, Pageable pageable);
}
