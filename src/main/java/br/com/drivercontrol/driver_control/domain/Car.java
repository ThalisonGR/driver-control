package br.com.drivercontrol.driver_control.domain;

import br.com.drivercontrol.driver_control.domain.valueobject.Money;
import br.com.drivercontrol.driver_control.domain.valueobject.TypeTransaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Car {

    private final UUID id;
    private String plate;
    private Long currentMileage;
    private final List<Transaction> transactions;

    public Car(UUID id, String plate, Long kmInitial) {
        this.id = id;
        this.plate = plate;
        this.currentMileage = kmInitial;
        this.transactions = new ArrayList<>();
    }

    public Car(String plate, Long kmInitial) {
        this(UUID.randomUUID(), plate, kmInitial);
    }

    // Business: Record a transaction
    public void addTransaction(Transaction transaction) {
        if (transaction.getKm() != null) {
            if (transaction.getKm() < this.currentMileage) {
                throw new IllegalArgumentException("Transaction mileage cannot be lower than current mileage.");
            }
            this.currentMileage = transaction.getKm();
        }
        this.transactions.add(transaction);
    }

    // KM driven since the last fuel supply ref
    public Long kmDrivenSinceLastSupply() {
        return transactions.stream()
                .filter(t -> t.getType() == TypeTransaction.Supply)
                .reduce((first, second) -> second)
                .map(t -> this.currentMileage - t.getKm())
                .orElse(0L);
    }

    // Total spent on fuel since the last supply
    public BigDecimal totalSuppliesSinceLast(String currency) {
        return transactions.stream()
                .filter(t -> t.getType() == TypeTransaction.Supply)
                .reduce((first, second) -> second)
                .map(t -> t.getValue().amount())
                .orElse(BigDecimal.ZERO);
    }

    // Total spent on all supplies
    public BigDecimal totalSpentOnSupplies(String currency) {
        return transactions.stream()
                .filter(t -> t.getType() == TypeTransaction.Supply)
                .map(t -> t.getValue().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Cost per KM driven (fuel expenses / KM driven)
    public BigDecimal costPerKm(String currency) {
        Long kmDriven = kmDrivenSinceLastSupply();
        if (kmDriven == 0L) {
            return BigDecimal.ZERO;
        }

        BigDecimal fuelSpent = totalSpentOnSupplies(currency);
        return fuelSpent.divide(BigDecimal.valueOf(kmDriven), 4, RoundingMode.HALF_UP);
    }

    // Cash flow balance (income - expenses)
    public BigDecimal cashFlowBalance(String currency) {
        BigDecimal income = transactions.stream()
                .filter(t -> t.getType() == TypeTransaction.UberIncome)
                .map(t -> t.getValue().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expenses = transactions.stream()
                .filter(t -> t.getType() != TypeTransaction.UberIncome)
                .map(t -> t.getValue().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return income.subtract(expenses);
    }

    // Total expenses (non-income)
    public BigDecimal totalExpenses(String currency) {
        return transactions.stream()
                .filter(t -> t.getType() != TypeTransaction.UberIncome)
                .map(t -> t.getValue().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Total income
    public BigDecimal totalIncome(String currency) {
        return transactions.stream()
                .filter(t -> t.getType() == TypeTransaction.UberIncome)
                .map(t -> t.getValue().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updatePlate(String newPlate) {
        this.plate = newPlate;
    }

    public void updateMileage(Long newMileage) {
        if (newMileage != null && newMileage < this.currentMileage) {
            throw new IllegalArgumentException("New mileage cannot be lower than current mileage.");
        }
        this.currentMileage = newMileage;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public UUID getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public Long getCurrentMileage() {
        return currentMileage;
    }
}
