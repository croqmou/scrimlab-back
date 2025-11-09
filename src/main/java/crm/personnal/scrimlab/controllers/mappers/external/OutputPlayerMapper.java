package crm.personnal.scrimlab.controllers.mappers.external;

import crm.personnal.scrimlab.config.domain.InputMapper;
import crm.personnal.scrimlab.config.domain.OutputMapper;
import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.springframework.stereotype.Component;

@Component
public class OutputPlayerMapper implements OutputMapper<OutputPlayerDTO, PlayerBO> {

    @Override
    public PlayerBO mapToBO(OutputPlayerDTO entity) {
        return new PlayerBO(
                entity.username(),
                null,
                entity.email(),
                entity.pp(),
                entity.admin(),
                entity.playerGoals(),
                entity.playerWins(),
                entity.playerLoses(),
                entity.rankingPoints1s(),
                entity.rankingPoints2s(),
                entity.rankingPoints3s()
        );
    }

    @Override
    public OutputPlayerDTO mapFromBO(PlayerBO playerBO) {
        return new OutputPlayerDTO(
                playerBO.getUsername(),
                playerBO.getEmail(),
                playerBO.getPp(),
                playerBO.isAdmin(),
                playerBO.getPlayerGoals(),
                playerBO.getPlayerWins(),
                playerBO.getPlayerLoses(),
                playerBO.getRankingPoints1s(),
                playerBO.getRankingPoints2s(),
                playerBO.getRankingPoints3s()
        );
    }
}
