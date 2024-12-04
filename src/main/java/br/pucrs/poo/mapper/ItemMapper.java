package br.pucrs.poo.mapper;
import br.pucrs.poo.dto.ItemDTO;
import br.pucrs.poo.entity.Item;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ItemMapper {
    ItemDTO toItemDTO(Item item);
}