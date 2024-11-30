package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
@Entity
@Table(name = "ITEM")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME",nullable = false)
    private String nome;
    @Column(name = "PRECO",nullable = false)
    private BigDecimal preco;
}
