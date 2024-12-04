package br.pucrs.poo.service;
import br.pucrs.poo.dto.ClienteDTO;
import br.pucrs.poo.entity.Cliente;
import br.pucrs.poo.mapper.ClienteMapper;
import br.pucrs.poo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    public ClienteDTO cadastrarCliente(ClienteDTO cliente) {
        Optional<Cliente> byCpf = buscarCliente(cliente.cpf());
        if (byCpf.isPresent()) throw new RuntimeException("Cliente com esse CPF já cadastrado!");
        Cliente entity = clienteMapper.toCliente(cliente);
        entity.setDataCadastro(LocalDateTime.now());
        Cliente save = clienteRepository.save(entity);
        System.out.println(save);
        return clienteMapper.toClienteDTO(save);
    }
    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toClienteDTO)
                .collect(Collectors.toList());
    }
    public ClienteDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toClienteDTO)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
    public List<ClienteDTO> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(clienteMapper::toClienteDTO)
                .collect(Collectors.toList());
    }
    public Optional<ClienteDTO> buscarClientesPorCPF(String cpf) {
        return buscarCliente(cpf)
                .map(c->new ClienteDTO(c.getNome(),c.getCpf(),c.getTelefone(),c.getEmail()));
    }
    protected Optional<Cliente> buscarCliente(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }
}
