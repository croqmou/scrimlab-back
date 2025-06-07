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
                playerBO.getUsername(),
                playerBO.getPwd(),
                playerBO.getEmail(),
                playerBO.getPp(),
                playerBO.isAdmin(),
                playerBO.getRankingPoints1s(),
                playerBO.getRankingPoints2s(),
                playerBO.getRankingPoints3s()
        );
    }
}
