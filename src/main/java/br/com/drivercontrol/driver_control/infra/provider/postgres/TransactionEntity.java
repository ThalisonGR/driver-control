package br.com.drivercontrol.driver_control.infra.provider.postgres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "t_transaction")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {

    @Id
    private UUID id;

    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "amount_")
    private BigDecimal value;

    private String currency;

    private String type;

    private Long km;

    private String description;

    private LocalDateTime createdAt;
}
