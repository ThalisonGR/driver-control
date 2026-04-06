package br.com.drivercontrol.driver_control.domain.valueobject;

import br.com.drivercontrol.driver_control.domain.exceptions.MoneyExeception;

import java.math.BigDecimal;

public record Money(BigDecimal amount, String currency) {

    public Money {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new MoneyExeception("Amount cannot be negative!");
        }
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount), this.currency);
    }
}