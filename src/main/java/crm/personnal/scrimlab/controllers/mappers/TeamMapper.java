package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements Mapper<TeamDTO, TeamBO> {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public TeamBO mapToBO(TeamDTO entity) {
        return new TeamBO(
                entity.teamName(),
                entity.teamLogo(),
                entity.teamDescription(),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.captain(), null, false, 0, 0, 0)),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.playerEntityTwo(), null, false, 0, 0, 0)),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.playerEntityThree(), null, false, 0, 0, 0)),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.sub(), null, false, 0, 0, 0)),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.secondSub(), null, false, 0, 0, 0)),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.coach(), null, false, 0, 0, 0)),
                playerMapper.mapToBO(new PlayerDTO(null, null, entity.manager(), null, false, 0, 0, 0)),
                entity.rankingPoints()
        );
    }

    @Override
    public TeamDTO mapFromBO(TeamBO teamBO) {
        return new TeamDTO(
                teamBO.getTeamName(),
                teamBO.getTeamLogo(),
                teamBO.getTeamDescription(),
                teamBO.getCaptain().getEmail(),
                teamBO.getPlayerEntityTwo().getEmail(),
                teamBO.getPlayerEntityThree().getEmail(),
                teamBO.getSub().getEmail(),
                teamBO.getSecondSub().getEmail(),
                teamBO.getCoach().getEmail(),
                teamBO.getManager().getEmail(),
                teamBO.getRankingPoints()
        );
    }
}
