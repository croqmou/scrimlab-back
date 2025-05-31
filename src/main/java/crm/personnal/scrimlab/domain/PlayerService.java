package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    PlayerBO addPlayer(PlayerBO playerBO);
}
