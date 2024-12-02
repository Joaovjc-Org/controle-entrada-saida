package br.pucrs.poo.controller;

import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.service.BalanceteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class BalanceteController {
    @Autowired

    private final BalanceteService balanceteService;



    public List<GastoTotalDTO> gerarBalanceteDiario() {
        return balanceteService.gerarBalanceteDiario();
    }

    public void fecharDia() {
        balanceteService.fecharDia();
        List<GastoTotalDTO> balancete = gerarBalanceteDiario();
    }
}
