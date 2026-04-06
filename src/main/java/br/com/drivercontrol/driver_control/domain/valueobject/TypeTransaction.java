package br.com.drivercontrol.driver_control.domain.valueobject;

import br.com.drivercontrol.driver_control.domain.exceptions.TransactionsExeption;

public record TypeTransaction(String nameTransaction, Boolean requiresMileage) {

    public static final TypeTransaction Supply = new TypeTransaction("Supply", true);
    public static final TypeTransaction Maintenance = new TypeTransaction("Maintenance", true);
    public static final TypeTransaction Tax = new TypeTransaction("Tax", false);
    public static final TypeTransaction Others = new TypeTransaction("Others", false);
    public static final TypeTransaction UberIncome = new TypeTransaction("UberIncome", false);

    public TypeTransaction {
        if (nameTransaction == null || nameTransaction.isBlank()) {
            throw new TransactionsExeption("Transaction type name is required.");
        }
    }

    public void validateLaunch(Long kmInformed) {
        if (this.requiresMileage && (kmInformed == null || kmInformed <= 0)) {
            throw new TransactionsExeption("For type " + nameTransaction + ", mileage is required.");
        }
    }

}
