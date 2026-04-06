package br.com.drivercontrol.driver_control.infra.adapter;

import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.Transaction;
import br.com.drivercontrol.driver_control.domain.TransactionRepository;
import br.com.drivercontrol.driver_control.infra.provider.h2.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransactionAdapter implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;
    private final MapStructMapper mapper;

    @Override
    public void save(Transaction transaction, Car car) {
        var entity = mapper.toEntityFromTransaction(transaction, car);
        jpaRepository.save(entity);
    }

    @Override
    public List<Transaction> findByCarId(UUID carId) {
        return jpaRepository.findByCarId(carId)
                .stream()
                .map(mapper::toDomainFromEntity)
                .toList();
    }
}
