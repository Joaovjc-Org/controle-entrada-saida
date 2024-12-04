package br.pucrs.poo.mapper;

import br.pucrs.poo.dto.FolhaDTO;
import br.pucrs.poo.entity.Folha;
import java.time.format.DateTimeFormatter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T15:13:56-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class FolhaMapperImpl implements FolhaMapper {

    @Override
    public FolhaDTO toFolhaDTO(Folha folha) {
        if ( folha == null ) {
            return null;
        }

        String id = null;
        String dataAbertura = null;

        if ( folha.getId() != null ) {
            id = String.valueOf( folha.getId() );
        }
        if ( folha.getDataAbertura() != null ) {
            dataAbertura = DateTimeFormatter.ISO_LOCAL_DATE.format( folha.getDataAbertura() );
        }

        FolhaDTO folhaDTO = new FolhaDTO( id, dataAbertura );

        return folhaDTO;
    }
}
