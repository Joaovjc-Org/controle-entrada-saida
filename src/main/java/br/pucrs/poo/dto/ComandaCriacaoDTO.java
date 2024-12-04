package br.pucrs.poo.dto;
import br.pucrs.poo.entity.Cliente;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@AllArgsConstructor
@ToString
@Builder
public class ComandaCriacaoDTO {
    private String codigoComanda;
    private LocalDateTime dataCriacao;
    @Builder.Default
    private BigDecimal gastoTotal = BigDecimal.ZERO;
}