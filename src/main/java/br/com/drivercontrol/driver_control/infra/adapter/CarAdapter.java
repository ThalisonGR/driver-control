package br.com.drivercontrol.driver_control.infra.adapter;

import br.com.drivercontrol.driver_control.domain.Car;
import br.com.drivercontrol.driver_control.domain.CarRepository;
import br.com.drivercontrol.driver_control.domain.model.car.Car;
import br.com.drivercontrol.driver_control.domain.model.car.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CarAdapter implements CarRepository {

    private final SpringDataCarRepository jpaRepository; // A interface do Spring Data

    @Override
    public void save(Car car) {
        // 1. Converte Dominio -> Entidade (Aqui entrará o MapStruct depois)
        CarEntity entity = toEntity(car);

        // 2. Salva no banco
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Car> findById(UUID id) {
        // 1. Busca Entidade
        // 2. Converte Entidade -> Dominio e retorna
        return jpaRepository.findById(id).map(this::toDomain);
    }

    // Métodos de conversão manual enquanto o MapStruct não chega
    private CarEntity toEntity(Car car) {
        // Mapeia os dados do objeto de domínio para a sua @Entity JPA
        return new CarEntity(car.getId(), car.getPlaca(), car.getQuilometragemAtual());
    }

    private Car toDomain(CarEntity entity) {
        return new Car(entity.getId(), entity.getPlaca(), entity.getKm());
    }
}
