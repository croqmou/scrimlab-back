package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.springframework.stereotype.Service;

@Service
public interface TeamService {
    TeamBO addTeam(TeamBO teamBO) throws Exception;
}
