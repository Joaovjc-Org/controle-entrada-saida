package br.pucrs.poo.view.item;

import br.pucrs.poo.controller.ItemController;
import br.pucrs.poo.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemWorker extends SwingWorker<List<ItemDTO>, Void> {
    private final ItemController controller;
    @Override
    protected List<ItemDTO> doInBackground() {
        return controller.recuperarTodosItensCadastrados();
    }
}
