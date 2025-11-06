package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public interface TeamService {
    TeamBO createTeam(TeamBO teamBO) throws TeamAlreadyExistsException, CaptainNotFoundException;
    Page<TeamBO> getAllTeams(Pageable pageable);
}
