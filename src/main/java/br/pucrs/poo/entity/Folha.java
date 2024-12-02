package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
@Entity
@Table(name = "FOLHA")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Folha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATA_ABERTURA", nullable = false)
    private LocalDate dataAbertura;
    @Column(name = "DATA_FECHAMENTO", nullable = false)
    private LocalDate dataFechamento;
    @Column(name = "FECHADO")
    private boolean fechado;
    
}