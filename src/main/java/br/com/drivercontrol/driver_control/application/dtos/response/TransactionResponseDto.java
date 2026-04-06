package br.com.drivercontrol.driver_control.application.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Response data for a transaction")
public record TransactionResponseDto(
        @Schema(description = "Transaction ID") UUID id,
        @Schema(description = "Car ID") UUID carId,
        @Schema(description = "Transaction amount") BigDecimal value,
        @Schema(description = "Currency code") String currency,
        @Schema(description = "Transaction type") String type,
        @Schema(description = "Mileage at time") Long km,
        @Schema(description = "Description") String description,
        @Schema(description = "Created at") LocalDateTime createdAt
) {}
