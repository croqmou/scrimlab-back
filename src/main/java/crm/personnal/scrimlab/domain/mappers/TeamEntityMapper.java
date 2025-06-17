package crm.personnal.scrimlab.domain.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.config.domain.OutputMapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamEntityMapper implements Mapper<TeamEntity, TeamBO> {
    @Autowired
    private PlayerEntityMapper playerEntityMapper;

    @Override
    public TeamBO mapToBO(TeamEntity teamEntity) {
        return new TeamBO(
                teamEntity.getTeamName(),
                teamEntity.getTeamLogo(),
                teamEntity.getTeamDescription(),
                playerEntityMapper.mapToBO(teamEntity.getCaptain()),
                playerEntityMapper.mapToBO(teamEntity.getPlayerEntityTwo()),
                playerEntityMapper.mapToBO(teamEntity.getPlayerEntityThree()),
                playerEntityMapper.mapToBO(teamEntity.getSub()),
                playerEntityMapper.mapToBO(teamEntity.getSecondSub()),
                playerEntityMapper.mapToBO(teamEntity.getCoach()),
                playerEntityMapper.mapToBO(teamEntity.getManager()),
                teamEntity.getRankingPoints()
        );
    }

    @Override
    public TeamEntity mapFromBO(TeamBO bo) {
        TeamEntity newTeamEntity = new TeamEntity();

        newTeamEntity.setTeamName(bo.getTeamName());
        newTeamEntity.setTeamLogo(bo.getTeamLogo());
        newTeamEntity.setTeamDescription(bo.getTeamDescription());
        newTeamEntity.setCaptain(playerEntityMapper.mapFromBO(bo.getCaptain()));
        newTeamEntity.setPlayerTwo(playerEntityMapper.mapFromBO(bo.getPlayerEntityTwo()));
        newTeamEntity.setPlayerThree(playerEntityMapper.mapFromBO(bo.getPlayerEntityThree()));
        newTeamEntity.setSub(playerEntityMapper.mapFromBO(bo.getSub()));
        newTeamEntity.setSecondSub(playerEntityMapper.mapFromBO(bo.getSecondSub()));
        newTeamEntity.setCoach(playerEntityMapper.mapFromBO(bo.getCoach()));
        newTeamEntity.setManager(playerEntityMapper.mapFromBO(bo.getManager()));

        return newTeamEntity;
    }

}

