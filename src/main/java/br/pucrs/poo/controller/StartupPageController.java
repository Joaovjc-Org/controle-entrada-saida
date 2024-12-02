package br.pucrs.poo.controller;

import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.entity.Gasto;
import br.pucrs.poo.service.BalanceteService;
import br.pucrs.poo.service.ComandaService;

import java.util.List;

public class StartupPageController {

    private final ComandaService comandaService;
    private final BalanceteService balanceteService;

    public StartupPageController(ComandaService comandaService, BalanceteService balanceteService) {
        this.comandaService = comandaService;
        this.balanceteService = balanceteService;
    }

    public void fecharConta(String codigoComanda) {
        comandaService.fecharConta(codigoComanda);
    }

    public List<GastoTotalDTO> gerarBalanceteDiario() {
        return balanceteService.gerarBalanceteDiario();
    }
}
