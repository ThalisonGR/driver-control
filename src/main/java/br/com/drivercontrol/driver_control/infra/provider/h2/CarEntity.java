package br.com.drivercontrol.driver_control.infra.provider.h2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String placa;

    @Column(name = "km_inicial")
    private Long kmInicial;

    @Column(name = "km_atual")
    private Long kmAtual;
}
