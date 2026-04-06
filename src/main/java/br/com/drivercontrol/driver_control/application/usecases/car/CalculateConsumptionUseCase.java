package br.com.drivercontrol.driver_control.application.usecases.car;

import br.com.drivercontrol.driver_control.application.dtos.response.ConsumptionResponseDto;
import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalculateConsumptionUseCase {

    private final CarRepository carRepository;

    public ConsumptionResponseDto execute(UUID carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + carId));

        Long kmDriven = car.kmDrivenSinceLastSupply();
        BigDecimal costPerKm = car.costPerKm("BRL");
        BigDecimal cashFlowBalance = car.cashFlowBalance("BRL");
        BigDecimal totalSpentOnSupplies = car.totalSpentOnSupplies("BRL");

        return new ConsumptionResponseDto(
                car.getId(),
                car.getPlate(),
                kmDriven,
                totalSpentOnSupplies,
                costPerKm,
                cashFlowBalance
        );
    }
}
