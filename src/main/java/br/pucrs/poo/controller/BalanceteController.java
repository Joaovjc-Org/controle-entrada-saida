package br.pucrs.poo.controller;

import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.service.BalanceteService;

import java.util.List;

public class BalanceteController {

    private final BalanceteService balanceteService;

    public BalanceteController(BalanceteService balanceteService) {
        this.balanceteService = balanceteService;
    }

    public List<GastoTotalDTO> gerarBalanceteDiario() {
        return balanceteService.gerarBalanceteDiario();
    }

    public void fecharDia() {
        balanceteService.fecharDia();
        List<GastoTotalDTO> balancete = gerarBalanceteDiario();
    }
}
