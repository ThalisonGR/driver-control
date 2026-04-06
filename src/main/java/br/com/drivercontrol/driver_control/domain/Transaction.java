package br.com.drivercontrol.driver_control.domain;

import br.com.drivercontrol.driver_control.domain.valueobject.Money;
import br.com.drivercontrol.driver_control.domain.valueobject.TypeTransaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private final UUID id;
    private final Money value;
    private final TypeTransaction type;
    private final Long km;
    private String description;
    private LocalDateTime createdAt;

    public Transaction(Money value, TypeTransaction type, Long km) {
        this(UUID.randomUUID(), value, type, km, null, LocalDateTime.now());
    }

    public Transaction(UUID id, Money value, TypeTransaction type, Long km, String description, LocalDateTime createdAt) {
        if (value == null) {
            throw new IllegalArgumentException("Transaction value is required.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Transaction type is required.");
        }

        this.id = id;
        this.value = value;
        this.type = type;
        this.km = km;
        this.description = description;
        this.createdAt = createdAt;
    }

    public void validateLaunch() {
        this.type.validateLaunch(this.km);
    }

    public UUID getId() {
        return id;
    }

    public Money getValue() {
        return value;
    }

    public TypeTransaction getType() {
        return type;
    }

    public Long getKm() {
        return km;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
