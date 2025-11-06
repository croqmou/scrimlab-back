package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.data.repositories.TeamRepository;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import crm.personnal.scrimlab.domain.mappers.TeamEntityMapper;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamEntityMapper teamEntityMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;


    @Override
    public TeamBO createTeam(TeamBO teamBO) throws TeamAlreadyExistsException, CaptainNotFoundException {

        if (teamRepository.existsById(teamBO.getTeamName())) {
            throw new TeamAlreadyExistsException("Team already exists");
        }

        PlayerEntity captain = playerRepository.findById(teamBO.getCaptain().getEmail())
                .orElseThrow(() -> new CaptainNotFoundException("Captain not found"));

        TeamEntity teamEntity = teamEntityMapper.mapFromBO(teamBO);
        teamEntity.setCaptain(captain);

        return teamEntityMapper.mapToBO(teamRepository.save(teamEntity));
    }

    @Override
    public Page<TeamBO> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable).map(teamEntityMapper::mapToBO);
    }
}
