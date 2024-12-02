package br.pucrs.poo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComandaDTO {
    private Long id;
    private String codigoComanda;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataPagamento;
    private List<Long> gastosIds; // IDs dos gastos associados à comanda
    private Long clienteId;       // ID do cliente associado
    private Long folhaId;         // ID da folha associada
}
