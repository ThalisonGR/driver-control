package br.com.drivercontrol.driver_control.application.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Request data for creating a transaction")
public record TransactionRequestDto(
        @Schema(description = "ID of the car associated with this transaction", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID carId,
        @Schema(description = "Transaction amount", example = "200.50")
        BigDecimal value,
        @Schema(description = "Currency code", example = "BRL")
        String currency,
        @Schema(description = "Transaction type: Supplu, Maintenance, Tax, Others", example = "Supplu")
        String typeTransaction,
        @Schema(description = "Current vehicle mileage at the time of transaction", example = "50000")
        Long km,
        @Schema(description = "Optional description of the transaction", example = "Gasolina - Posto Shell")
        String description
) {}
