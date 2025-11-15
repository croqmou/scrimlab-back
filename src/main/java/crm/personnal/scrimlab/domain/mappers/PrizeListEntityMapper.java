package crm.personnal.scrimlab.domain.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.PrizeListEntity;
import crm.personnal.scrimlab.data.entities.embadded.PrizelistId;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import org.springframework.stereotype.Component;

@Component
public class PrizeListEntityMapper implements Mapper<PrizeListEntity, PrizeListBO> {

    @Override
    public PrizeListBO mapToBO(PrizeListEntity entity) {
        return new PrizeListBO(
                entity.getPlayer().getEmail(),
                entity.getId().getTournamentName(),
                entity.getResult(),
                entity.getTournamentDate()
        );
    }

    @Override
    public PrizeListEntity mapFromBO(PrizeListBO bo) {
        PrizeListEntity newPrizeListEntity = new PrizeListEntity();
        PrizelistId prizelistId = new PrizelistId();
        prizelistId.setPlayer(bo.getPlayerEmail());
        prizelistId.setTournamentName(bo.getPrizeListName());
        newPrizeListEntity.setId(prizelistId);
        newPrizeListEntity.setResult(bo.getResult());
        newPrizeListEntity.setTournamentDate(bo.getTournamentDate());

        return newPrizeListEntity;
    }

}

