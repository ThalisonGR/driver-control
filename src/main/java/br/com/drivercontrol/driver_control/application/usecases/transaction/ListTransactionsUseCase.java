package br.com.drivercontrol.driver_control.application.usecases.transaction;

import br.com.drivercontrol.driver_control.application.dtos.response.TransactionResponseDto;
import br.com.drivercontrol.driver_control.domain.TransactionRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListTransactionsUseCase {

    private final TransactionRepository transactionRepository;
    private final MapStructMapper mapper;

    public List<TransactionResponseDto> execute(UUID carId) {
        var transactions = transactionRepository.findByCarId(carId);
        return transactions.stream()
                .map(t -> mapper.toResponseDto(t, carId))
                .toList();
    }
}
