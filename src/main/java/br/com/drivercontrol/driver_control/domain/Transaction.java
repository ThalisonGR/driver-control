package br.com.drivercontrol.driver_control.domain;

import br.com.drivercontrol.driver_control.domain.valueobject.Money;
import br.com.drivercontrol.driver_control.domain.valueobject.TypeTransaction;
import lombok.Getter;

@Getter
public class Transaction {

    private final Money value;
    private final TypeTransaction tipo;
    private final Long km;

    public Transaction(Money value, TypeTransaction tipo, Long km) {
        if (value == null) {
            throw new IllegalArgumentException("O valor da transação é obrigatório.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo da transação é obrigatório.");
        }

        this.value = value;
        this.tipo = tipo;
        this.km = km;
    }
}