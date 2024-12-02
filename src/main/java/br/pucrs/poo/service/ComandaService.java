package br.pucrs.poo.service;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.entity.Gasto;
import br.pucrs.poo.mapper.ComandaMapper;
import br.pucrs.poo.repository.ComandaRepository;
import br.pucrs.poo.dto.BalanceteDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Map;

public class ComandaService {

    private final ComandaRepository comandaRepository;

    
    public ComandaService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO) {
        // Verificar se o cliente já possui uma comanda ativa.
        Optional<Comanda> comandaAtiva = comandaRepository
            .findByClienteIdAndDataPagamentoIsNull(comandaDTO.getClienteId());
        if (comandaAtiva.isPresent()) {
            throw new RuntimeException("O cliente já possui uma comanda ativa!");
        }

        // Gerar código único para a comanda.
        String codigoComanda = UUID.randomUUID().toString();

        ComandaDTO comandaComData = new ComandaDTO(
            null,
            codigoComanda,
            LocalDateTime.now(),
            null,
            null,
            comandaDTO.getClienteId(),
            comandaDTO.getFolhaId()
        );

        Comanda comanda = ComandaMapper.INSTANCE.toComanda(comandaComData);
        Comanda salva = comandaRepository.save(comanda);
        return ComandaMapper.INSTANCE.toComandaDTO(salva);
    }

    public ComandaDTO buscarComandaPorId(Long id) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isEmpty()) {
            throw new RuntimeException("Comanda não encontrada!");
        }
        return ComandaMapper.INSTANCE.toComandaDTO(comandaOptional.get());
    }

    public void adicionarDebito(String codigoComanda, Gasto gasto) {
        // Buscar comanda pelo código.
        Optional<Comanda> comandaOptional = comandaRepository.findByCodigoComanda(codigoComanda);
        if (comandaOptional.isEmpty()) {
            throw new RuntimeException("Comanda não encontrada para o código fornecido!");
        }

        Comanda comanda = comandaOptional.get();
        comanda.getGastos().add(gasto);
        comandaRepository.save(comanda); // Atualiza a comanda com o novo débito.
    }


    
}
