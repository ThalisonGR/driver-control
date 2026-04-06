package br.com.drivercontrol.driver_control.application.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCarRequestDto(
        @Schema(description = "Vehicle plate number", example = "ABC1D23")
        String plate,
        @Schema(description = "Current mileage", example = "10000")
        Long mileage
) {}
