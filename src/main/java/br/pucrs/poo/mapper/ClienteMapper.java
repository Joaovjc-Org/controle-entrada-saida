package br.pucrs.poo.mapper;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ClienteMapper{
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    Cliente toCliente(ClienteDTO cliente);
    ClienteDTO toClienteDTO(Cliente cliente);
}
