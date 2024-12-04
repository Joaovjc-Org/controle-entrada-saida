package br.pucrs.poo.mapper;

import br.pucrs.poo.dto.CodigoComandaDTO;
import br.pucrs.poo.dto.ComandaCriacaoDTO;
import br.pucrs.poo.entity.Cliente;
import br.pucrs.poo.entity.Comanda;
import br.pucrs.poo.entity.Folha;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T15:13:57-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class ComandaMapperImpl implements ComandaMapper {

    @Override
    public CodigoComandaDTO toComandaDTO(Comanda comanda) {
        if ( comanda == null ) {
            return null;
        }

        String codigoComanda = null;

        codigoComanda = comanda.getCodigoComanda();

        CodigoComandaDTO codigoComandaDTO = new CodigoComandaDTO( codigoComanda );

        return codigoComandaDTO;
    }

    @Override
    public Comanda toComanda(ComandaCriacaoDTO comanda, Cliente clienteParam, Folha folhaParam) {
        if ( comanda == null && clienteParam == null && folhaParam == null ) {
            return null;
        }

        Comanda.ComandaBuilder comanda1 = Comanda.builder();

        if ( comanda != null ) {
            comanda1.codigoComanda( comanda.getCodigoComanda() );
            comanda1.dataCriacao( comanda.getDataCriacao() );
        }
        comanda1.cliente( clienteParam );
        comanda1.folha( folhaParam );

        return comanda1.build();
    }
}
