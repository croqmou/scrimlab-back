package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public interface TeamService {
    TeamBO createTeam(TeamBO teamBO) throws TeamAlreadyExistsException, CaptainNotFoundException;
    Page<TeamBO> getAllTeams(Pageable pageable);
    List<TeamBO> getAllTeamsByPlayer(String email);
}
