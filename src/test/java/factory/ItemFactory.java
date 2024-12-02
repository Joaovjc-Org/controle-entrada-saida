package factory;

import br.pucrs.poo.dto.ItemDTO;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

public class ItemFactory {
    public static List<ItemDTO> itens(){
        return IntStream.range(0,5)
                .mapToObj(ItemFactory::item)
                .toList();
    }

    private static ItemDTO item(int i) {
        return ItemDTO.builder()
                .nome("ITEM: "+i)
                .preco(new BigDecimal(i).multiply(new BigDecimal(5000)))
                .build();
    }
}
