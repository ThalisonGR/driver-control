package br.com.drivercontrol.driver_control.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Car {

    private UUID id;
    private String placa;
    private Long quilometragemAtual;
    private List<Transaction> transacoes;

    public Car(UUID id, String placa, Long kmInicial) {
        this.id = id;
        this.placa = placa;
        this.quilometragemAtual = kmInicial;
        this.transacoes = new ArrayList<>();
    }

    public Car(String placa, Long kmInicial) {
        this(UUID.randomUUID(), placa, kmInicial);
    }

    // Comportamento de Negócio: Lançar Valor
    public void lancarGasto(Transaction transaction) {
        // Regra de Negócio: Se a transação informar KM, ela não pode ser retroativa
        if (transaction.getKm() != null) {
            if (transaction.getKm() < this.quilometragemAtual) {
                throw new IllegalArgumentException("KM do lançamento é menor que a quilometragem atual do carro.");
            }
            // Atualiza a quilometragem do carro com base no lançamento
            this.quilometragemAtual = transaction.getKm();
        }

        this.transacoes.add(transaction);
    }

    public void atualizarPlaca(String novaPlaca) {
        this.placa = novaPlaca;
    }

    public void atualizarQuilometragem(Long novaKm) {
        if (novaKm != null && novaKm < this.quilometragemAtual) {
            throw new IllegalArgumentException("A nova KM não pode ser menor que a KM atual.");
        }
        this.quilometragemAtual = novaKm;
    }

    public List<Transaction> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    public UUID getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public Long getQuilometragemAtual() {
        return quilometragemAtual;
    }
}
