package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.data.repositories.TeamRepository;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import crm.personnal.scrimlab.domain.mappers.TeamEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamEntityMapper teamEntityMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerEntityMapper  playerEntityMapper;

    @Override
    public TeamBO addTeam(TeamBO teamBO) throws Exception {

       teamBO.setCaptain(playerEntityMapper.mapToBO //TODO doit retourner un optional
               (playerRepository.getReferenceById(
                       teamBO.getCaptain().getEmail()
               ))
       );

        if (teamRepository.existsById(teamBO.getTeamName())) {
            throw new Exception(); //TODO Faire une exception personnalisée
        }

        TeamEntity teamEntity = teamEntityMapper.mapFromBO(teamBO);


        teamEntity.setPlayerTwo(null);
        teamEntity.setPlayerThree(null);
        teamEntity.setSub(null);
        teamEntity.setSecondSub(null);
        teamEntity.setCoach(null);
        teamEntity.setManager(null);
        teamEntity.setRankingPoints(0);

        return teamEntityMapper.mapToBO(teamRepository.save(teamEntity));
    }
}
