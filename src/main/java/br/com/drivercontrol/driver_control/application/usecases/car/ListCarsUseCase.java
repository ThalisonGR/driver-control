package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCarsUseCase {

    private final CarRepository carRepository;
    private final MapStructMapper mapper;

    public Page<CarResponseDto> execute(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(mapper::toCarResponseDto);
    }
}
