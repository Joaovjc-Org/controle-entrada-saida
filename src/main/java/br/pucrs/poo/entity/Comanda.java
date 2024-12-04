package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "COMANDA")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CODIGO_COMANDA", nullable = false, unique = true)
    private String codigoComanda;
    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "DATA_PAGAMENTO")
    private LocalDateTime dataPagamento;
    @Column(name = "GASTO_TOTAL")
    private BigDecimal gastoTotal;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Gasto.class)
    private List<Gasto> gastos;
    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
    @ManyToOne(targetEntity = Folha.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLHA_ID")
    private Folha folha;
    public void addAllGastos(Collection <Gasto> gastos){
        this.gastos.addAll(gastos);
    }
}