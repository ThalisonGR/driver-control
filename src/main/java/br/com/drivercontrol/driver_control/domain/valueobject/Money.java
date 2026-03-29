package br.com.drivercontrol.driver_control.domain.valueobject;

import br.com.drivercontrol.driver_control.domain.exceptions.MoneyExeception;

import java.math.BigDecimal;

public record Money(BigDecimal quantity, String moeda) {

    public Money {
        if (quantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new MoneyExeception("Dinheiro não pode ser negativo!");
        }
    }

    public Money somar(Money outro) {
        return new Money(this.quantity.add(outro.quantity), this.moeda);
    }
}