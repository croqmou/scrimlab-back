package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.dto.TeamFullDTO;
import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.external.OutputPlayerMapper;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamFullMapper implements Mapper<TeamFullDTO, TeamBO> {

    @Autowired
    private OutputPlayerMapper outputPlayerMapper;

    @Override
    public TeamBO mapToBO(TeamFullDTO entity) {
        return new TeamBO(
                entity.teamName(),
                entity.teamLogo(),
                entity.teamBanner(),
                entity.teamDescription(),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.captain().username(), entity.captain().email(),entity.captain().pp(),entity.captain().admin(),entity.captain().playerGoals(),entity.captain().playerWins(),entity.captain().playerLoses(),entity.captain().rankingPoints1s(),entity.captain().rankingPoints2s(),entity.captain().rankingPoints3s())),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.playerEntityTwo().username(), entity.playerEntityTwo().email(),entity.playerEntityTwo().pp(),entity.playerEntityTwo().admin(),entity.playerEntityTwo().playerGoals(),entity.playerEntityTwo().playerWins(),entity.playerEntityTwo().playerLoses(),entity.playerEntityTwo().rankingPoints1s(),entity.playerEntityTwo().rankingPoints2s(),entity.playerEntityTwo().rankingPoints3s())),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.playerEntityThree().username(), entity.playerEntityThree().email(),entity.playerEntityThree().pp(),entity.playerEntityThree().admin(),entity.playerEntityThree().playerGoals(),entity.playerEntityThree().playerWins(),entity.playerEntityThree().playerLoses(),entity.playerEntityThree().rankingPoints1s(),entity.playerEntityThree().rankingPoints2s(),entity.playerEntityThree().rankingPoints3s())),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.sub().username(), entity.sub().email(),entity.sub().pp(),entity.sub().admin(),entity.sub().playerGoals(),entity.sub().playerWins(),entity.sub().playerLoses(),entity.sub().rankingPoints1s(),entity.sub().rankingPoints2s(),entity.sub().rankingPoints3s())),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.secondSub().username(), entity.secondSub().email(),entity.secondSub().pp(),entity.secondSub().admin(),entity.secondSub().playerGoals(),entity.secondSub().playerWins(),entity.secondSub().playerLoses(),entity.secondSub().rankingPoints1s(),entity.secondSub().rankingPoints2s(),entity.secondSub().rankingPoints3s())),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.coach().username(), entity.coach().email(),entity.coach().pp(),entity.coach().admin(),entity.coach().playerGoals(),entity.coach().playerWins(),entity.coach().playerLoses(),entity.coach().rankingPoints1s(),entity.coach().rankingPoints2s(),entity.coach().rankingPoints3s())),
                outputPlayerMapper.mapToBO(new OutputPlayerDTO(entity.manager().username(), entity.manager().email(),entity.manager().pp(),entity.manager().admin(),entity.manager().playerGoals(),entity.manager().playerWins(),entity.manager().playerLoses(),entity.manager().rankingPoints1s(),entity.manager().rankingPoints2s(),entity.manager().rankingPoints3s())),
                entity.rankingPoints(),
                entity.teamGoals(),
                entity.teamWins(),
                entity.teamLoses()
        );
    }

    @Override
    public TeamFullDTO mapFromBO(TeamBO teamBO) {
        return new TeamFullDTO(
                teamBO.getTeamName(),
                teamBO.getTeamLogo(),
                teamBO.getTeamBanner(),
                teamBO.getTeamDescription(),
                outputPlayerMapper.mapFromBO(teamBO.getCaptain()),
                teamBO.getPlayerEntityTwo() == null ? null : outputPlayerMapper.mapFromBO(teamBO.getPlayerEntityTwo()),
                teamBO.getPlayerEntityThree() == null ? null : outputPlayerMapper.mapFromBO(teamBO.getPlayerEntityThree()),
                teamBO.getSub() == null ? null : outputPlayerMapper.mapFromBO(teamBO.getSub()),
                teamBO.getSecondSub() == null ? null : outputPlayerMapper.mapFromBO(teamBO.getSecondSub()),
                teamBO.getCoach() == null ? null : outputPlayerMapper.mapFromBO(teamBO.getCoach()),
                teamBO.getManager() == null ? null : outputPlayerMapper.mapFromBO(teamBO.getManager()),
                teamBO.getRankingPoints(),
                teamBO.getTeamGoals(),
                teamBO.getTeamWins(),
                teamBO.getTeamLoses()
        );
    }
}
