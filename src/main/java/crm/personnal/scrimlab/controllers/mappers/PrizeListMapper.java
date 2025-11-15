package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.controllers.dto.PrizeListDTO;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrizeListMapper implements Mapper<PrizeListDTO, PrizeListBO> {

    @Override
    public PrizeListBO mapToBO(PrizeListDTO entity) {
        return new PrizeListBO(
                entity.playerEmail(),
                entity.prizeListName(),
                entity.result(),
                entity.tournamentDate()
        );
    }

    @Override
    public PrizeListDTO mapFromBO(PrizeListBO prizeListBO) {
        return new PrizeListDTO(
                prizeListBO.getPlayerEmail(),
                prizeListBO.getPrizeListName(),
                prizeListBO.getResult(),
                prizeListBO.getTournamentDate()
        );
    }
}
