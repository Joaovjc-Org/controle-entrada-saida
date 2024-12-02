package br.pucrs.poo.dto;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ItemDTO(String nome, BigDecimal preco) {
}
