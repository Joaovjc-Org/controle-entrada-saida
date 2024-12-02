package br.pucrs.poo.service;

import br.pucrs.poo.dto.BalanceteDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.repository.ComandaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BalanceteService {

    private final ComandaRepository comandaRepository;

    public BalanceteService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    public List<BalanceteDTO> gerarBalanceteDiario() {
        // Recuperar todas as comandas do dia
        List<Comanda> comandasDoDia = comandaRepository.findAllByDataAtual();

        // Agrupar os gastos por cliente
        Map<String, BigDecimal> gastosPorCliente = comandasDoDia.stream()
                .collect(Collectors.groupingBy(
                        Comanda::getNomeCliente,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                comanda -> comanda.getItens().stream()
                                        .map(item -> item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                                        .reduce(BigDecimal.ZERO, BigDecimal::add),
                                BigDecimal::add
                        )
                ));

        // Converter para uma lista de DTOs
        return gastosPorCliente.entrySet().stream()
                .map(entry -> new BalanceteDTO(entry.getKey(), entry.getValue()))
                .toList();
    }
}
