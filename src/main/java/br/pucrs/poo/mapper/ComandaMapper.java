package br.pucrs.poo.mapper;
import br.pucrs.poo.dto.ComandaCriacaoDTO;
import br.pucrs.poo.dto.ComandaDTO;
import br.pucrs.poo.entity.Cliente;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.entity.Folha;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ComandaMapper {
    ComandaDTO toComandaDTO(Comanda comanda);
    @Mapping(target = "cliente", source = "clienteParam")
    @Mapping(target = "folha", source = "folhaParam")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataPagamento", ignore = true)
    @Mapping(target = "gastoTotal", ignore = true)
    @Mapping(target = "gastos", ignore = true)
    Comanda toComanda(ComandaCriacaoDTO comanda, Cliente clienteParam, Folha folhaParam);
}