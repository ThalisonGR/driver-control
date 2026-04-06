package br.com.drivercontrol.driver_control.infra.web.controller;

import br.com.drivercontrol.driver_control.application.dtos.request.CreateCarRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.request.UpdateCarRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.application.usecases.car.CreateCarUseCase;
import br.com.drivercontrol.driver_control.application.usecases.car.DeleteCarUseCase;
import br.com.drivercontrol.driver_control.application.usecases.car.FindCarByIdUseCase;
import br.com.drivercontrol.driver_control.application.usecases.car.ListCarsUseCase;
import br.com.drivercontrol.driver_control.application.usecases.car.UpdateCarUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Tag(name = "Cars", description = "CRUD operations for managing vehicles")
public class CarController {

    private final CreateCarUseCase createCar;
    private final ListCarsUseCase listCars;
    private final FindCarByIdUseCase findCarById;
    private final UpdateCarUseCase updateCar;
    private final DeleteCarUseCase deleteCar;

    @PostMapping
    @Operation(summary = "Create a new car", description = "Registers a new car with plate and initial mileage")
    @ApiResponse(responseCode = "201", description = "Car created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    public ResponseEntity<CarResponseDto> create(@RequestBody CreateCarRequestDto dto) {
        var response = createCar.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "List all cars", description = "Returns all registered cars")
    @ApiResponse(responseCode = "200", description = "List of cars retrieved successfully")
    public ResponseEntity<List<CarResponseDto>> list() {
        return ResponseEntity.ok(listCars.execute());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find car by ID", description = "Returns a car by its ID")
    @ApiResponse(responseCode = "200", description = "Car found successfully")
    @ApiResponse(responseCode = "404", description = "Car not found")
    public ResponseEntity<CarResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(findCarById.execute(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a car", description = "Updates plate or mileage of an existing car")
    @ApiResponse(responseCode = "200", description = "Car updated successfully")
    @ApiResponse(responseCode = "404", description = "Car not found")
    public ResponseEntity<CarResponseDto> update(@PathVariable UUID id, @RequestBody UpdateCarRequestDto dto) {
        return ResponseEntity.ok(updateCar.execute(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a car", description = "Removes a car from the system")
    @ApiResponse(responseCode = "204", description = "Car deleted successfully")
    @ApiResponse(responseCode = "404", description = "Car not found")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteCar.execute(id);
        return ResponseEntity.noContent().build();
    }
}
