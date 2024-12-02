package br.pucrs.poo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class BalanceteDTO {
    private String nomeCliente;
    private BigDecimal totalGasto;
}
