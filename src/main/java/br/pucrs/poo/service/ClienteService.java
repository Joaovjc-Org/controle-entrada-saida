package br.pucrs.poo.service;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.entity.Cliente;
import br.pucrs.poo.mapper.ClienteMapper;
import br.pucrs.poo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public ClienteDTO cadastrarCliente(ClienteDTO cliente) {
        Optional<Cliente> byCpf = clienteRepository.findByCpf(cliente.cpf());
        if (byCpf.isPresent()) throw new RuntimeException("placeholder");
        Cliente entity = ClienteMapper.INSTANCE.toCliente(cliente);
        Cliente save = clienteRepository.save(entity);
        return ClienteMapper.INSTANCE.toClienteDTO(save);
    }
}
