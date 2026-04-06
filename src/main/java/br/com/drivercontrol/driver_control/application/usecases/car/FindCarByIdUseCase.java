package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindCarByIdUseCase {

    private final CarRepository carRepository;
    private final MapStructMapper mapper;

    public CarResponseDto execute(UUID id) {
        var car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));
        return mapper.toCarResponseDto(car);
    }
}
