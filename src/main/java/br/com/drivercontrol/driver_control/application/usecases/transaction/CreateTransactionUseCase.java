package br.com.drivercontrol.driver_control.application.usecases.transaction;

import br.com.drivercontrol.driver_control.application.dtos.request.TransactionRequestDto;
import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.domain.Transaction;
import br.com.drivercontrol.driver_control.domain.TransactionRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final CarRepository carRepository;
    private final MapStructMapper mapper;

    public void execute(TransactionRequestDto dto) {
        Car car = carRepository.findById(dto.carId())
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com ID: " + dto.carId()));

        Transaction transaction = mapper.toDomainFromDto(dto);
        transaction.validateLaunch();

        car.lancarGasto(transaction);
        carRepository.save(car);
        transactionRepository.save(transaction, car);
    }
}
