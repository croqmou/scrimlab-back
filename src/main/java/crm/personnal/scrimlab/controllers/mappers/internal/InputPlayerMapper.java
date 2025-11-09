package crm.personnal.scrimlab.controllers.mappers.internal;

import crm.personnal.scrimlab.config.domain.InputMapper;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.springframework.stereotype.Component;

@Component
public class InputPlayerMapper implements InputMapper<InputPlayerDTO, PlayerBO> {

    @Override
    public PlayerBO mapToBO(InputPlayerDTO entity) {
        return new PlayerBO(
                entity.username(),
                entity.pwd(),
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
    public InputPlayerDTO mapFromBO(PlayerBO playerBO) {
        return new InputPlayerDTO(
                playerBO.getUsername(),
                playerBO.getPwd(),
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
