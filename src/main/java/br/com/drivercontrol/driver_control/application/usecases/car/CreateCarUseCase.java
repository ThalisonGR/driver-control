package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.application.dtos.request.CreateCarRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCarUseCase {

    private final CarRepository carRepository;
    private final MapStructMapper mapper;

    public CarResponseDto execute(CreateCarRequestDto dto) {
        Car car = mapper.createCarFromDto(dto);
        carRepository.save(car);
        return mapper.toCarResponseDto(car);
    }
}
