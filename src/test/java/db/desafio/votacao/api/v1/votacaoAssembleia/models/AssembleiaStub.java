package db.desafio.votacao.api.v1.votacaoAssembleia.models;

import com.db.desafio.votacao.api.v1.config.ApplicationContext;
import com.db.desafio.votacao.api.v1.modules.votacaoAssembleia.database.models.Assembleia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AssembleiaStub
{
    /**
     * createAssembleiaJson
     * 
     * @return String
     * @throws JsonProcessingException
     */
    public static String createAssembleiaJson() throws JsonProcessingException
    {
        Assembleia assembleia = new Assembleia();

        assembleia.setName("Assembleia para teste");
        assembleia.setStartDate( ApplicationContext.today() );
        assembleia.setEndDate( ApplicationContext.today().plusDays( 1 ));

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString( assembleia );
    }
}
