package br.com.drivercontrol.driver_control.application.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCarRequestDto(
        @Schema(description = "Vehicle plate number", example = "ABC1D23")
        String placa,
        @Schema(description = "Initial mileage", example = "0")
        Long quilometragemActual
) {}