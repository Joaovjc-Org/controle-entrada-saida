package br.pucrs.poo.service;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.mapper.ComandaMapper;
import br.pucrs.poo.repository.ComandaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class ComandaService {

    private final ComandaRepository comandaRepository;

    public ComandaService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    public ComandaDTO criarComanda(ComandaDTO comandaDTO) {
        ComandaDTO comandaComData = new ComandaDTO(
            comandaDTO.getId(),
            comandaDTO.getCodigoComanda(),
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
}
