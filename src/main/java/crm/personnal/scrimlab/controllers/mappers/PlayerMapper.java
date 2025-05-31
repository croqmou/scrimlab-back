package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements Mapper<PlayerDTO, PlayerBO> {

    @Override
    public PlayerBO mapToBO(PlayerDTO entity) {
        return new PlayerBO(
                entity.username(),
                entity.pwd(),
                entity.email(),
                entity.pp(),
                entity.admin(),
                entity.rankingPoints1s(),
                entity.rankingPoints2s(),
                entity.rankingPoints3s()
        );
    }

    @Override
    public PlayerDTO mapFromBO(PlayerBO playerBO) {
        return new PlayerDTO(
                playerBO.username(),
                playerBO.pwd(),
                playerBO.email(),
                playerBO.pp(),
                playerBO.admin(),
                playerBO.rankingPoints1s(),
                playerBO.rankingPoints2s(),
                playerBO.rankingPoints3s()
        );
    }
}
