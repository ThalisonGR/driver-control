package br.com.drivercontrol.driver_control.application.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Response data for a transaction")
public record TransactionResponseDto(
        @Schema(description = "Transaction ID") UUID id,
        @Schema(description = "ID of the associated car") UUID carId,
        @Schema(description = "Transaction amount") BigDecimal value,
        @Schema(description = "Currency code") String currency,
        @Schema(description = "Transaction type name") String typeTransaction,
        @Schema(description = "Vehicle mileage at the time") Long km,
        @Schema(description = "Transaction description") String description,
        @Schema(description = "When the transaction was created") LocalDateTime createdAt
) {}
