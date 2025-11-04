package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public interface TeamService {
    TeamBO createTeam(TeamBO teamBO) throws Exception;
    Page<TeamBO> getAllTeams(Pageable pageable);
}
