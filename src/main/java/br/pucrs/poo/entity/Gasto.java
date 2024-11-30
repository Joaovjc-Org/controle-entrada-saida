package br.pucrs.poo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
@Entity
@Table(name = "GASTO")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Item.class)
    private List<Item> itemGasto;
}
