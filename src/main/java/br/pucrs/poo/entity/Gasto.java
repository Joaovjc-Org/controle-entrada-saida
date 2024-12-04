package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(doNotUseGetters = true)
@Entity
@Table(name = "GASTO")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
    @JoinColumn(name = "ITEM_ID")
    private Item item;
    @Column(name="VALOR_PAGO", nullable = false)
    private BigDecimal valorPago;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Comanda.class)
    @JoinColumn(name = "COMANDA_ID")
    private Comanda Comanda;
}