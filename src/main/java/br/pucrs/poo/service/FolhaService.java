package br.pucrs.poo.service;
import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.entity.Folha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import br.pucrs.poo.repository.FolhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolhaService {
    @Autowired
    private FolhaRepository folhaRepository;

    public List<GastoTotalDTO> gerarBalanceteDiario() {
        List<Comanda> comandasDoDia = recuperarComandasDoDia();

        return comandasDoDia.stream()
                .map(comanda-> new GastoTotalDTO(comanda.getCliente().getNome(), comanda.getGastoTotal()))
                .toList();
    }
    public boolean checarFolhaDoDiaAberta() {
        return recuperarFolhaDoDia()
                .map(folha -> !folha.isFechado())
                .orElse(false);
    }
    private List<Comanda> recuperarComandasDoDia() {
        return recuperarFolhaDoDia()
                .map(Folha::getComandas)
                .orElseThrow(() -> new RuntimeException("Nao existe folha aberta para o dia de hoje"));
    }
    public void fecharDia() {
        Folha folha = recuperarFolhaDoDia()
                .orElseThrow(() -> new RuntimeException("Nao existe folha para o dia de hoje"));
        if(folha.isFechado())throw new RuntimeException("A folha ja foi fechada");
        folha.setFechado(true);
        folhaRepository.save(folha);
    }
    protected Optional<Folha> recuperarFolhaDoDia() {
        return folhaRepository.findByDataAberturaAndFechadoIsFalse(LocalDate.now());
    }
    public void iniciarNovoDia() {
        Optional<Folha> folha = recuperarFolhaDoDia();
        if(folha.isPresent()) throw new RuntimeException("Ja existe uma folha para o dia de hoje");
        Folha folhaNova = new Folha();
        folhaNova.setDataAbertura(LocalDate.now());
        folhaRepository.save(folhaNova);
    }
}
