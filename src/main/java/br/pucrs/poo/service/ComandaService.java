package br.pucrs.poo.service;
import br.pucrs.poo.dto.CodigoComandaDTO;
import br.pucrs.poo.dto.ComandaCriacaoDTO;
import br.pucrs.poo.entity.*;
import br.pucrs.poo.mapper.ComandaMapper;
import br.pucrs.poo.repository.ComandaRepository;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ComandaService {
    private final ComandaRepository comandaRepository;
    private final ItemService itemService;
    private final FolhaService folhaService;
    private final ClienteService clienteService;
    private final ComandaMapper comandaMapper;

    public void adicionarItem(String codigoComanda, Long itemId, int quantidade) {
        Comanda comanda = comandaRepository.findByCodigoComanda(codigoComanda)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada!"));

        Item item = itemService.recuperarItemPorId(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        BigDecimal valorTotalItem = item.getPreco().multiply(BigDecimal.valueOf(quantidade));

        List<Gasto> gastos = IntStream.range(0, quantidade)
                .mapToObj(i -> Gasto.builder()
                        .item(item)
                        .valorPago(item.getPreco())
                        .build())
                .toList();


        comanda.addAllGastos(gastos);

        comanda.setGastoTotal(comanda.getGastoTotal().add(valorTotalItem));
        comandaRepository.save(comanda);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public CodigoComandaDTO criarComanda(String cpf) {
        Optional<Folha> folha = folhaService.recuperarFolhaDoDiaOptional();
        if (folha.isEmpty()) throw new RuntimeException("Nao foi aberta uma folha!");
        Cliente cliente = clienteService.buscarCliente(cpf)
                .orElseThrow(() -> new RuntimeException("Problemas ao recuperar o cliente"));
        Optional<Comanda> comandaAtiva = comandaRepository
                .findByClienteIdAndDataPagamentoIsNull(cliente.getId());
        if (comandaAtiva.isPresent()) {
            throw new RuntimeException("O cliente já possui uma comanda ativa!");
        }
        ComandaCriacaoDTO comandaDTO = ComandaCriacaoDTO.builder()
                .codigoComanda(String.valueOf(UUID.randomUUID()))
                .dataCriacao(LocalDateTime.now())
                .build();

        Comanda comanda = comandaMapper.toComanda(comandaDTO,cliente,folha.get());
        Comanda salva = comandaRepository.save(comanda);
        System.out.println(salva);
        return comandaMapper.toComandaDTO(salva);
    }

    public CodigoComandaDTO buscarComandaPorId(Long id) {
        Optional<Comanda> comandaOptional = comandaRepository.findById(id);
        if (comandaOptional.isEmpty()) {
            throw new RuntimeException("Comanda não encontrada!");
        }
        return comandaMapper.toComandaDTO(comandaOptional.get());
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

    public void fecharConta(String codigoComanda) {
        // Buscar comanda pelo código.
        Optional<Comanda> comandaOptional = comandaRepository.findByCodigoComanda(codigoComanda);
        if (comandaOptional.isEmpty()) {
            throw new RuntimeException("Comanda não encontrada para o código fornecido!");
        }

        Comanda comanda = comandaOptional.get();

        comanda.setDataPagamento(LocalDateTime.now());

        comandaRepository.save(comanda); // Atualiza a comanda com as informações de fechamento.
    }

    public BigDecimal fecharComanda(Long comandaId) {
        Comanda comanda = comandaRepository.findById(comandaId)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada!"));

        if (comanda.getDataPagamento() != null) {
            throw new RuntimeException("A comanda já foi paga!");
        }

        comanda.setDataPagamento(LocalDateTime.now());
        comandaRepository.save(comanda);

        return comanda.getGastoTotal();
    }

}
