package br.pucrs.poo.mapper;
import br.pucrs.poo.dto.FolhaDTO;
import br.pucrs.poo.entity.Folha;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FolhaMapper {
    FolhaDTO toFolhaDTO(Folha folha);
}