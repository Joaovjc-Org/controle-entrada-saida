package br.pucrs.poo.mapper;

import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.entity.Cliente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-03T18:32:33-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toCliente(ClienteDTO cliente) {
        if ( cliente == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente1 = Cliente.builder();

        cliente1.cpf( cliente.cpf() );
        cliente1.nome( cliente.nome() );
        cliente1.email( cliente.email() );
        cliente1.telefone( cliente.telefone() );

        return cliente1.build();
    }

    @Override
    public ClienteDTO toClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        String nome = null;
        String cpf = null;
        String telefone = null;
        String email = null;

        nome = cliente.getNome();
        cpf = cliente.getCpf();
        telefone = cliente.getTelefone();
        email = cliente.getEmail();

        ClienteDTO clienteDTO = new ClienteDTO( nome, cpf, telefone, email );

        return clienteDTO;
    }
}
