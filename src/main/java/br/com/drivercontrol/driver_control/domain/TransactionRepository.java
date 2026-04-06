package br.com.drivercontrol.driver_control.domain;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {

    void save(Transaction transaction, Car car);

    List<Transaction> findByCarId(UUID carId);
}
