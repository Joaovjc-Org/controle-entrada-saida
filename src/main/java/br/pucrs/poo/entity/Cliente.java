package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CLIENTE")
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
    @Builder.Default
    private LocalDateTime dataCadastro = LocalDateTime.now();
    @OneToMany(mappedBy = "cliente")
    private List<Comanda> comandas;
}