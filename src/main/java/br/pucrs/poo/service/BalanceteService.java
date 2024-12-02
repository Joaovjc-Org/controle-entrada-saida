package br.pucrs.poo.service;
import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.repository.ComandaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceteService {
    @Autowired
    private ComandaRepository comandaRepository;

    public BalanceteService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    public BigDecimal calcularBalancete(List<Comanda> comandas) {
        return comandas.stream()
            .map(Comanda::getGastoTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public List<GastoTotalDTO> gerarBalanceteDiario() {


        List<Comanda> comandasDoDia = recuperarComandasDoDia();

        // Agrupar os gastos por cliente
        List<GastoTotalDTO> gastosPorCliente = comandasDoDia.stream()
                .map(comanda-> new GastoTotalDTO(comanda.getCliente().getNome(), comanda.getGastoTotal()))
                .toList();
        
        return gastosPorCliente;
    }

    private List<Comanda> recuperarComandasDoDia() {
        LocalDateTime startDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59,59));
        List<Comanda> comandasDoDia = comandaRepository.findAllByDataCriacaoBetween(startDate, LocalDateTime.now());
        return comandasDoDia;
    }
    public void fecharDia() {

        List<Comanda> comandasDoDia = recuperarComandasDoDia();


        for (Comanda comanda : comandasDoDia) {

            System.out.println("Fechando comanda: " + comanda.getId() + " com total: " + comanda.getGastoTotal());

        }


    }
}
