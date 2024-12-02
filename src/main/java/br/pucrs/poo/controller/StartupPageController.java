package br.pucrs.poo.controller;
import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.service.BalanceteService;
import br.pucrs.poo.service.ComandaService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Component
public class StartupPageController {
    private final ComandaService comandaService;
    private final BalanceteService balanceteService;

    public void fecharConta(String codigoComanda) {
        comandaService.fecharConta(codigoComanda);
    }

    public List<GastoTotalDTO> gerarBalanceteDiario() {
        return balanceteService.gerarBalanceteDiario();
    }

    public void fecharDia() {
        try {
            comandaService.fecharDia();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void iniciarNovoDia() {
        try {
            comandaService.iniciarNovoDia();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fazerPedido(Long comandaId, Long itemId, int quantidade) {
        comandaService.adicionarItem(comandaId, itemId, quantidade);
        System.out.println("Item adicionado com sucesso à comanda.");
    }


}
