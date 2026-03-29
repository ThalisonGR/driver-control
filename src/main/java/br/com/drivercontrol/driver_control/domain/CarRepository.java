package br.com.drivercontrol.driver_control.domain;

public interface CarRepository {

    // Salva ou Atualiza o Agregado completo (Car + Transactions)
    void save(Car car);

    // Busca o carro por ID trazendo seu estado atual
    Optional<Car> findById(UUID id);

    // Busca por placa (comum em regras de negócio de veículos)
    Optional<Car> findByPlaca(String placa);

    // Remove o veículo e suas dependências
    void delete(UUID id);
}