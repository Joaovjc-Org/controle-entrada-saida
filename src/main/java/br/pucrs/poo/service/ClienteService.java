package br.pucrs.poo.service;

import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.entity.Cliente;
import br.pucrs.poo.mapper.ClienteMapper;
import br.pucrs.poo.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO cadastrarCliente(ClienteDTO cliente) {
        Optional<Cliente> byCpf = clienteRepository.findByCpf(cliente.cpf());
        if (byCpf.isPresent()) throw new RuntimeException("Cliente com esse CPF já cadastrado!");
        Cliente entity = ClienteMapper.INSTANCE.toCliente(cliente);
        Cliente save = clienteRepository.save(entity);
        return ClienteMapper.INSTANCE.toClienteDTO(save);
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .map(ClienteMapper.INSTANCE::toClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper.INSTANCE::toClienteDTO)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<ClienteDTO> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(ClienteMapper.INSTANCE::toClienteDTO)
                .collect(Collectors.toList());
    }
}
