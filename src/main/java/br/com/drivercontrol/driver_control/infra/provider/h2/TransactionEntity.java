package br.com.drivercontrol.driver_control.infra.provider.h2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "car_id")
    private UUID carId;

    private BigDecimal value;

    private String currency;

    private String typeTransaction;

    private Long km;

    private String description;

    private LocalDateTime createdAt;
}
