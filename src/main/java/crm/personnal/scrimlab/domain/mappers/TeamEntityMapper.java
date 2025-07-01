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
                teamEntity.getPlayerEntityTwo() == null ? null : playerEntityMapper.mapToBO(teamEntity.getPlayerEntityTwo()),
                teamEntity.getPlayerThree() == null ? null : playerEntityMapper.mapToBO(teamEntity.getPlayerThree()),
                teamEntity.getSub() == null ? null : playerEntityMapper.mapToBO(teamEntity.getSub()),
                teamEntity.getSecondSub() == null ? null : playerEntityMapper.mapToBO(teamEntity.getSecondSub()),
                teamEntity.getCoach() == null ? null : playerEntityMapper.mapToBO(teamEntity.getCoach()),
                teamEntity.getManager() == null ? null : playerEntityMapper.mapToBO(teamEntity.getManager()),
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

