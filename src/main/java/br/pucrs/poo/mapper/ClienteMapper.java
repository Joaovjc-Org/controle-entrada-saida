package br.pucrs.poo.mapper;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.entity.Cliente;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClienteMapper{
    Cliente toCliente(ClienteDTO cliente);
    ClienteDTO toClienteDTO(Cliente cliente);
}
