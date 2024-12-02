package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "GASTO")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Builder
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
    private Item item;
    @Column(name="VALOR_PAGO", nullable = false)
    private BigDecimal valorPago;
}