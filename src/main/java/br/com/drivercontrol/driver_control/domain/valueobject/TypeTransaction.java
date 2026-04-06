package br.com.drivercontrol.driver_control.domain.valueobject;

import br.com.drivercontrol.driver_control.domain.exceptions.TransactionsExeption;

public record TypeTransaction(String nameTransaction, Boolean requiredMileage) {

    public static final TypeTransaction Supplu = new TypeTransaction("Supplu", true); //ABASTECIMENTO
    public static final TypeTransaction Maintenance = new TypeTransaction("Maintenance", true); // MANUTENCAO
    public static final TypeTransaction Tax = new TypeTransaction("Tax", false); //IMPOSTO
    public static final TypeTransaction Others = new TypeTransaction("Others", false); //OUTROS

    public TypeTransaction {
        if (nameTransaction == null || nameTransaction.isBlank()) {
            throw new TransactionsExeption("O nome do tipo de transação é obrigatório.");
        }
    }

    public void validedLaunch(Long kmInformed) {
        if (this.requiredMileage && (kmInformed == null || kmInformed <= 0)) {
            throw new TransactionsExeption("Para o tipo " + nameTransaction + ", a quilometragem é obrigatória.");
        }
    }

}
