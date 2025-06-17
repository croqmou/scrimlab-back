package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.data.repositories.TeamRepository;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.domain.mappers.TeamEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamEntityMapper teamEntityMapper;

    @Autowired
    private TeamRepository teamRepository;


    @Override
    public TeamBO addTeam(TeamBO teamBO) throws Exception {
        if (teamRepository.existsById(teamBO.getTeamName())) {
            throw new Exception(); //TODO Faire une exception personnalisée
        }

        TeamEntity teamEntity = teamEntityMapper.mapFromBO(teamBO);

        return teamEntityMapper.mapToBO(teamRepository.save(teamEntity));
    }
}
