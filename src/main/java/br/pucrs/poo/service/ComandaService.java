package br.pucrs.poo.service;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.entity.Gasto;
import br.pucrs.poo.entity.Item;
import br.pucrs.poo.mapper.ComandaMapper;
import br.pucrs.poo.repository.ComandaRepository;
import br.pucrs.poo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final ItemRepository itemRepository;

    public void adicionarItem(Long comandaId, Long itemId, int quantidade) {
        Comanda comanda = comandaRepository.findById(comandaId)
                .orElseThrow(() -> new RuntimeException("Comanda não encontrada!"));

        Item item = itemRepository.findById(itemId)
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

    private boolean sistemaAtivo = true;

    public void fecharDia() {
        if (!sistemaAtivo) {
            throw new RuntimeException("O dia já foi encerrado!");
        }

        sistemaAtivo = false; // Bloqueia novos pedidos.

        // Calcula o total de todas as comandas fechadas.
        List<Comanda> comandas = comandaRepository.findAllByDataPagamentoNotNull();
        BigDecimal totalDia = comandas.stream()
                .map(Comanda::getGastoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Exibe o balancete do dia.
        System.out.println("=== Balancete do Dia ===");
        System.out.println("Total arrecadado: " + totalDia);
        System.out.println("Número de comandas fechadas: " + comandas.size());
    }

    public void iniciarNovoDia() {
        sistemaAtivo = true; // Permite novos pedidos.
        System.out.println("Novo dia iniciado!");
    }

    // Valida se o sistema está ativo antes de aceitar novos pedidos.
    public void verificarSistemaAtivo() {
        if (!sistemaAtivo) {
            throw new RuntimeException("Não é possível cadastrar novos pedidos. O dia foi encerrado!");
        }
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
                comandaDTO.getFolhaId());

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

    public void fecharConta(String codigoComanda) {
        // Buscar comanda pelo código.
        Optional<Comanda> comandaOptional = comandaRepository.findByCodigoComanda(codigoComanda);
        if (comandaOptional.isEmpty()) {
            throw new RuntimeException("Comanda não encontrada para o código fornecido!");
        }

        Comanda comanda = comandaOptional.get();

        comanda.setDataFechamento(LocalDateTime.now()); // Supondo que há um campo data de fechamento

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
