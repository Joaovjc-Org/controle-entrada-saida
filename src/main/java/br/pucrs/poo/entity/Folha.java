package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(doNotUseGetters = true)
@Entity
@Table(name = "FOLHA")
public class Folha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATA_ABERTURA", nullable = false)
    private LocalDate dataAbertura;
    @Column(name = "DATA_FECHAMENTO")
    private LocalDate dataFechamento;
    @Column(name = "FECHADO")
    private boolean fechado;
    @OneToMany(targetEntity = Comanda.class, mappedBy = "folha")
    private List<Comanda> comandas;
}