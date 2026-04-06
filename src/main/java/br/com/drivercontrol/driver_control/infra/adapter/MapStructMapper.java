package br.com.drivercontrol.driver_control.infra.adapter;

import br.com.drivercontrol.driver_control.application.dtos.request.CreateCarRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.request.TransactionRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.request.UpdateCarRequestDto;
import br.com.drivercontrol.driver_control.application.dtos.response.CarResponseDto;
import br.com.drivercontrol.driver_control.application.dtos.response.TransactionResponseDto;
import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.Transaction;
import br.com.drivercontrol.driver_control.domain.valueobject.Money;
import br.com.drivercontrol.driver_control.domain.valueobject.TypeTransaction;
import br.com.drivercontrol.driver_control.infra.provider.h2.CarEntity;
import br.com.drivercontrol.driver_control.infra.provider.h2.TransactionEntity;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class MapStructMapper {

    // --- Domain <-> Entity: Car ---
    public CarEntity toEntity(Car car) {
        var entity = new CarEntity();
        entity.setId(car.getId());
        entity.setPlaca(car.getPlaca());
        entity.setKmInicial(car.getQuilometragemAtual());
        entity.setKmAtual(car.getQuilometragemAtual());
        return entity;
    }

    public Car toDomain(CarEntity entity) {
        return new Car(entity.getId(), entity.getPlaca(), entity.getKmAtual());
    }

    // --- Domain -> Entity: Transaction ---
    public TransactionEntity toEntityFromTransaction(Transaction transaction, Car car) {
        var entity = new TransactionEntity();
        entity.setCarId(car.getId());
        entity.setValue(transaction.getValue().quantity());
        entity.setCurrency(transaction.getValue().moeda());
        entity.setTypeTransaction(transaction.getTipo().nameTransaction());
        entity.setKm(transaction.getKm());
        entity.setDescription(transaction.getDescription());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    // --- Entity -> Domain: Transaction ---
    public Transaction toDomainFromEntity(TransactionEntity entity) {
        var money = new Money(entity.getValue(), entity.getCurrency());
        var type = resolveType(entity.getTypeTransaction());
        return new Transaction(
                entity.getId(),
                money,
                type,
                entity.getKm(),
                entity.getDescription(),
                entity.getCreatedAt()
        );
    }

    // --- DTO -> Domain ---
    public Transaction toDomainFromDto(TransactionRequestDto dto) {
        var money = new Money(dto.value(), dto.currency());
        var type = resolveType(dto.typeTransaction());
        return new Transaction(money, type, dto.km());
    }

    public Money toMoney(TransactionRequestDto dto) {
        return new Money(dto.value(), dto.currency());
    }

    public TypeTransaction toType(String name) {
        return resolveType(name);
    }

    // --- Car DTO <-> Domain ---
    public Car createCarFromDto(CreateCarRequestDto dto) {
        return new Car(dto.placa(), dto.quilometragemActual());
    }

    public CarResponseDto toCarResponseDto(Car car) {
        return new CarResponseDto(car.getId(), car.getPlaca(), car.getQuilometragemAtual());
    }

    public List<CarResponseDto> toCarResponseDtoList(List<Car> cars) {
        return cars.stream()
                .map(this::toCarResponseDto)
                .toList();
    }

    // --- Domain -> Response DTO ---
    public TransactionResponseDto toResponseDto(Transaction transaction, UUID carId) {
        return new TransactionResponseDto(
                transaction.getId(),
                carId,
                transaction.getValue().quantity(),
                transaction.getValue().moeda(),
                transaction.getTipo().nameTransaction(),
                transaction.getKm(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }

    private TypeTransaction resolveType(String name) {
        return switch (name) {
            case "Supplu" -> TypeTransaction.Supplu;
            case "Maintenance" -> TypeTransaction.Maintenance;
            case "Tax" -> TypeTransaction.Tax;
            case "Others" -> TypeTransaction.Others;
            default -> new TypeTransaction(name, false);
        };
    }
}
