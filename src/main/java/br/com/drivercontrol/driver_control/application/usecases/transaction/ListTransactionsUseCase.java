package br.com.drivercontrol.driver_control.application.usecases.transaction;

import br.com.drivercontrol.driver_control.application.dtos.response.TransactionResponseDto;
import br.com.drivercontrol.driver_control.domain.TransactionRepository;
import br.com.drivercontrol.driver_control.infra.adapter.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListTransactionsUseCase {

    private final TransactionRepository transactionRepository;
    private final MapStructMapper mapper;

    public Page<TransactionResponseDto> execute(UUID carId, Pageable pageable) {
        return transactionRepository.findByCarId(carId, pageable)
                .map(t -> mapper.toResponseDto(t, carId));
    }
}
