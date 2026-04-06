package br.com.drivercontrol.driver_control.application.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Response data for a car")
public record CarResponseDto(
        @Schema(description = "Car ID") UUID id,
        @Schema(description = "Vehicle plate") String placa,
        @Schema(description = "Current mileage") Long quilometragemActual
) {}