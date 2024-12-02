package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "COMANDA")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CODIGO_COMANDA", nullable = false, unique = true)
    private String codigoComanda;
    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "DATA_PAGAMENTO", nullable = false)
    private LocalDateTime dataPagamento;
    @OneToMany(fetch = FetchType.EAGER, targetEntity = Comanda.class)
    private List<Gasto> gastos;
    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.LAZY)
    private Cliente cliente;
    @ManyToOne(targetEntity = Folha.class, fetch = FetchType.LAZY)
    private Folha folha;
}