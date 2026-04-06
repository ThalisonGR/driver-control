package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.application.dtos.request.UpdateCarRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateCarUseCase {

    private final CarRepository carRepository;
    private final MapStructMapper mapper;

    public CarResponseDto execute(UUID id, UpdateCarRequestDto dto) {
        var car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));

        if (dto.plate() != null && !dto.plate().isBlank()) {
            car.updatePlate(dto.plate());
        }
        if (dto.mileage() != null) {
            car.updateMileage(dto.mileage());
        }

        carRepository.save(car);
        return mapper.toCarResponseDto(car);
    }
}
