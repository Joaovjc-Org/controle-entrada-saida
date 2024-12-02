package br.pucrs.poo.mapper;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemDTO toItemDTO(Item item);
}
