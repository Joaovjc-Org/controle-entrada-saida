package controller;

import br.pucrs.poo.controller.ItemController;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.service.ItemService;
import factory.ItemFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@TestInstance(Lifecycle.PER_CLASS)
public class ItemControllerTest {
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    private ItemController itemController;
    @Mock
    private ItemService itemService;
    @Test
    void deveriaRetornarOsItensCadastradosDoSistema(){
        List<ItemDTO> itens = ItemFactory.itens();
        when(itemService.recuperarTodosItens()).thenReturn(itens);
        List<ItemDTO> itensRecuperados = itemController.recuperarTodosItensCadastrados();
        assertThat(itensRecuperados)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(itens.toArray(ItemDTO[]::new));
    }
}
