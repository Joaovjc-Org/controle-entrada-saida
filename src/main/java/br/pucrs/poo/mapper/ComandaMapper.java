package br.pucrs.poo.mapper;

import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.entity.Comanda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComandaMapper {
    ComandaMapper INSTANCE = Mappers.getMapper(ComandaMapper.class);

    @Mapping(source = "gastos.id", target = "gastosIds") // Mapeia os IDs dos gastos
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "folha.id", target = "folhaId")
    ComandaDTO toComandaDTO(Comanda comanda);

    @Mapping(target = "gastos", ignore = true) // Ignora os relacionamentos complexos na criação
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "folha", ignore = true)
    Comanda toComanda(ComandaDTO comandaDTO);
}
