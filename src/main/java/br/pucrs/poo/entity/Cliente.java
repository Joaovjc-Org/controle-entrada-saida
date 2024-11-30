package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CPF",unique=true, nullable=false)
    private String cpf;
    @Column(name = "NOME", nullable=false)
    private String nome;
    @Column(name = "EMAIL", nullable=false)
    private String email;
    @Column(name = "TELEFONE", nullable=false)
    private String telefone;
    @Column(name = "DATA_CADASTRO", nullable=false)
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Comanda.class)
    private List<Comanda> comandas;
}