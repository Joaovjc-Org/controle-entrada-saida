package br.pucrs.poo.service;
import br.pucrs.poo.dto.FolhaDTO;
import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.entity.Folha;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.pucrs.poo.mapper.FolhaMapper;
import br.pucrs.poo.repository.FolhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FolhaService {
    @Autowired
    private FolhaRepository folhaRepository;
    @Autowired
    private FolhaMapper folhaMapper;
    @Transactional(propagation= Propagation.REQUIRED)
    public List<GastoTotalDTO> gerarBalanceteDiario() {
        List<Comanda> comandasDoDia = recuperarComandasDoDia();

        return comandasDoDia.stream()
                .map(comanda-> new GastoTotalDTO(comanda.getCodigoComanda(),
                        (comanda.getGastoTotal()!=null)?comanda.getGastoTotal().toPlainString():"0",
                        comanda.getCliente().getNome(),
                        comanda.getDataPagamento()!=null))
                .toList();
    }
    public boolean checarFolhaDoDiaAberta() {
        return recuperarFolhaDoDiaOptional()
                .map(folha -> !folha.isFechado())
                .orElse(false);
    }
    private List<Comanda> recuperarComandasDoDia() {
        return recuperarFolhaDoDiaOptional()
                .map(Folha::getComandas)
                .orElseThrow(() -> new RuntimeException("Nao existe folha aberta para o dia de hoje"));
    }
    public void fecharDia() {
        Folha folha = recuperarFolhaDoDiaOptional()
                .orElseThrow(() -> new RuntimeException("Nao existe folha para o dia de hoje"));
        if(folha.isFechado())throw new RuntimeException("A folha ja foi fechada");
        folha.setFechado(true);
        folhaRepository.save(folha);
    }
    protected Optional<Folha> recuperarFolhaDoDiaOptional() {
        return folhaRepository.findByDataAberturaAndFechadoIsFalse(LocalDate.now());
    }
    public FolhaDTO recuperarFolhaDoDia() {
        return folhaRepository.findByDataAberturaAndFechadoIsFalse(LocalDate.now())
                .map(folhaMapper::toFolhaDTO)
                .orElseThrow(()-> new RuntimeException("Nao existe folha para o dia de hoje"));
    }
    public void iniciarNovoDia() {
        Optional<Folha> folha = recuperarFolhaDoDiaOptional();
        if(folha.isPresent()) throw new RuntimeException("Ja existe uma folha para o dia de hoje");
        Folha folhaNova = new Folha();
        folhaNova.setDataAbertura(LocalDate.now());
        folhaRepository.save(folhaNova);
    }
}