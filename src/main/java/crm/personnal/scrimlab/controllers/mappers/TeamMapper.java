package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements Mapper<TeamDTO, TeamBO> {

    @Autowired
    private InputPlayerMapper inputPlayerMapper;

    @Override
    public TeamBO mapToBO(TeamDTO entity) {
        return new TeamBO(
                entity.teamName(),
                entity.teamLogo(),
                entity.teamBanner(),
                entity.teamDescription(),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.captain(), null, false,0,0,0, 0, 0, 0)),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.playerEntityTwo(), null, false,0,0,0, 0, 0, 0)),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.playerEntityThree(), null, false,0,0,0, 0, 0, 0)),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.sub(), null, false,0,0,0, 0, 0, 0)),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.secondSub(), null, false,0,0,0, 0, 0, 0)),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.coach(), null, false,0,0,0, 0, 0, 0)),
                inputPlayerMapper.mapToBO(new InputPlayerDTO(null, null, entity.manager(), null, false,0,0,0, 0, 0, 0)),
                entity.rankingPoints(),
                entity.teamGoals(),
                entity.teamWins(),
                entity.teamLoses()
        );
    }

    @Override
    public TeamDTO mapFromBO(TeamBO teamBO) {
        return new TeamDTO(
                teamBO.getTeamName(),
                teamBO.getTeamLogo(),
                teamBO.getTeamBanner(),
                teamBO.getTeamDescription(),
                teamBO.getCaptain() == null ? null : teamBO.getCaptain().getEmail(),
                teamBO.getPlayerEntityTwo() == null ? null : teamBO.getPlayerEntityTwo().getEmail(),
                teamBO.getPlayerEntityThree() == null ? null : teamBO.getPlayerEntityThree().getEmail(),
                teamBO.getSub() == null ? null : teamBO.getSub().getEmail(),
                teamBO.getSecondSub() == null ? null : teamBO.getSecondSub().getEmail(),
                teamBO.getCoach() == null ? null : teamBO.getCoach().getEmail(),
                teamBO.getManager() == null ? null : teamBO.getManager().getEmail(),
                teamBO.getRankingPoints(),
                teamBO.getTeamGoals(),
                teamBO.getTeamWins(),
                teamBO.getTeamLoses()
        );
    }
}
