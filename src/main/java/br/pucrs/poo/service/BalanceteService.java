package br.pucrs.poo.service;

import br.pucrs.poo.dto.GastoTotalDTO;
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

    public List<GastoTotalDTO> gerarBalanceteDiario() {
        // Recuperar todas as comandas do dia
        List<Comanda> comandasDoDia = comandaRepository.findAllByDataAtual();

        // Agrupar os gastos por cliente
        List<GastoTotalDTO> gastosPorCliente = comandasDoDia.stream()
                .map(comanda-> new GastoTotalDTO(comanda.getCliente().getNome(), comanda.getGastoTotal()))
                .toList();
        
        return gastosPorCliente;
    }
}
