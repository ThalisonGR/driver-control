package br.com.drivercontrol.driver_control.infra.provider.postgres;

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
    private UUID id;

    private String plate;

    @Column(name = "km_initial")
    private Long KMInitial;

    @Column(name = "km_current")
    private Long KMCurrent;
}
