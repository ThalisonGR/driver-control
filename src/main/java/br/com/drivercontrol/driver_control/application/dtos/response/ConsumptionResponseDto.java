package br.com.drivercontrol.driver_control.application.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Fuel consumption and cost metrics for a car")
public record ConsumptionResponseDto(
        @Schema(description = "Car ID") UUID carId,
        @Schema(description = "Vehicle plate") String plate,
        @Schema(description = "KM driven since last fuel supply") Long kmDriven,
        @Schema(description = "Total spent on fuel supplies") BigDecimal totalSpentOnSupplies,
        @Schema(description = "Cost per KM driven") BigDecimal costPerKm,
        @Schema(description = "Cash flow balance (income - expenses)") BigDecimal cashFlowBalance
) {}
