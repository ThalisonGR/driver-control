package br.com.drivercontrol.driver_control.infra.web.controller;

import br.com.drivercontrol.driver_control.application.dtos.request.TransactionRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.response.TransactionResponseDto;
import br.com.drivercontrol.driver_control.application.usecases.transaction.CreateTransactionUseCase;
import br.com.drivercontrol.driver_control.application.usecases.transaction.ListTransactionsUseCase;
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
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Operations for managing driver transactions (fuel, maintenance, etc.)")
public class TransactionController {

    private final CreateTransactionUseCase createTransaction;
    private final ListTransactionsUseCase listTransactions;

    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Registers a new transaction such as refueling, maintenance, tax, or others")
    @ApiResponse(responseCode = "201", description = "Transaction created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request data")
    public ResponseEntity<Void> create(@RequestBody TransactionRequestDto dto) {
        createTransaction.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "List transactions by car", description = "Returns all transactions for a specific car")
    @ApiResponse(responseCode = "200", description = "List of transactions retrieved successfully")
    public ResponseEntity<List<TransactionResponseDto>> listByCar(@RequestParam UUID carId) {
        var transactions = listTransactions.execute(carId);
        return ResponseEntity.ok(transactions);
    }
}
