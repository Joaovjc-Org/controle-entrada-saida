package br.pucrs.poo.controller;

import br.pucrs.poo.dto.GastoTotalDTO;
import br.pucrs.poo.service.FolhaService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class BalanceteController {
    @Autowired
    private final FolhaService folhaService;

    public List<GastoTotalDTO> gerarBalanceteDiario() {
        return folhaService.gerarBalanceteDiario();
    }

    public void fecharDia() {
        try {
            folhaService.fecharDia();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void iniciarNovoDia() {
        try {
            folhaService.iniciarNovoDia();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean checarFolhaDoDiaAberta() {
        return folhaService.checarFolhaDoDiaAberta();
    }
}
