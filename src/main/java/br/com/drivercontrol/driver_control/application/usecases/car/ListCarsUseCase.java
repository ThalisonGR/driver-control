package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCarsUseCase {

    private final CarRepository carRepository;
    private final MapStructMapper mapper;

    public List<CarResponseDto> execute() {
        var cars = carRepository.findAll();
        return cars.stream()
                .map(mapper::toCarResponseDto)
                .toList();
    }
}
