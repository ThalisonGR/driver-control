package br.com.drivercontrol.driver_control.domain;

import br.com.drivercontrol.driver_control.domain.valueobject.Money;
import br.com.drivercontrol.driver_control.domain.valueobject.TypeTransaction;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private final Money value;
    private final TypeTransaction tipo;
    private final Long km;
    private String description;
    private LocalDateTime createdAt;

    public Transaction(Money value, TypeTransaction tipo, Long km) {
        this(UUID.randomUUID(), value, tipo, km, null, LocalDateTime.now());
    }

    public Transaction(UUID id, Money value, TypeTransaction tipo, Long km, String description, LocalDateTime createdAt) {
        if (value == null) {
            throw new IllegalArgumentException("O valor da transação é obrigatório.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo da transação é obrigatório.");
        }

        this.id = id;
        this.value = value;
        this.tipo = tipo;
        this.km = km;
        this.description = description;
        this.createdAt = createdAt;
    }

    public void validateLaunch() {
        this.tipo.validedLaunch(this.km);
    }

    public UUID getId() {
        return id;
    }

    public Money getValue() {
        return value;
    }

    public TypeTransaction getTipo() {
        return tipo;
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
