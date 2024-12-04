package br.pucrs.poo.mapper;

import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.entity.Item;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T14:35:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDTO toItemDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO.ItemDTOBuilder itemDTO = ItemDTO.builder();

        itemDTO.nome( item.getNome() );
        itemDTO.preco( item.getPreco() );

        return itemDTO.build();
    }
}
