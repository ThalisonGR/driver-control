package br.com.drivercontrol.driver_control.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionRepository {

    void save(Transaction transaction, Car car);

    Page<Transaction> findByCarId(UUID carId, Pageable pageable);
}
