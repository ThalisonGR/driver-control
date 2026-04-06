package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.domain.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCarUseCase {

    private final CarRepository carRepository;

    public void execute(UUID id) {
        carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com ID: " + id));
        carRepository.delete(id);
    }
}
