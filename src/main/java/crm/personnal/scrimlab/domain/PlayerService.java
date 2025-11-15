package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.exceptions.PlayerNotFoundException;
import crm.personnal.scrimlab.exceptions.PrizeListAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    PrizeListBO createPrizeList(PrizeListBO prizeListBO) throws PlayerNotFoundException, PrizeListAlreadyExistsException;
    List<PrizeListBO> getAllPrizeList();
}
