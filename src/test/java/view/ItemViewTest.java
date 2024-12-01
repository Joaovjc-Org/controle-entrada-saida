package view;
import br.pucrs.poo.controller.ItemController;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.service.ItemService;
import br.pucrs.poo.view.ItemView;
import factory.ItemFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
@TestInstance(Lifecycle.PER_CLASS)
public class ItemViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
    }
    @InjectMocks
    private ItemView itemView;
    @Mock
    private ItemController itemController;
    @Test
    void deveriaRetornarOsItensCadastradosDoSistema(){
        List<ItemDTO> itens = ItemFactory.itens();
        when(itemController.recuperarTodosItensCadastrados()).thenReturn(itens);
        itemView.displayItens();
        assertThat(outContent.toString())
                .isEqualTo("\n--- Cardápio ---\n"+
                          "  1. ITEM: 0 - R$ 0,00\n"+
                          "  2. ITEM: 1 - R$ 5000,00\n"+
                          "  3. ITEM: 2 - R$ 10000,00\n"+
                          "  4. ITEM: 3 - R$ 15000,00\n"+
                          "  5. ITEM: 4 - R$ 20000,00"+
                        "\n");
    }
}
