package br.pucrs.poo.controller;

import br.pucrs.poo.dto.BalanceteDTO;
import br.pucrs.poo.service.BalanceteService;

import java.util.List;

public class BalanceteController {

    private final BalanceteService balanceteService;

    public BalanceteController(BalanceteService balanceteService) {
        this.balanceteService = balanceteService;
    }

    public List<BalanceteDTO> gerarBalanceteDiario() {
        return balanceteService.gerarBalanceteDiario();
    }
}
