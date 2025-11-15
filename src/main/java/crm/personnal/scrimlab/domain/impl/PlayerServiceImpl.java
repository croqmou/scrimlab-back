package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.PrizeListEntity;
import crm.personnal.scrimlab.data.entities.embadded.PrizelistId;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.data.repositories.PrizeListRepository;
import crm.personnal.scrimlab.domain.PlayerService;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.domain.mappers.PrizeListEntityMapper;
import crm.personnal.scrimlab.exceptions.PlayerNotFoundException;
import crm.personnal.scrimlab.exceptions.PrizeListAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PrizeListEntityMapper prizeListEntityMapper;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PrizeListRepository prizeListRepository;

    @Override
    public PrizeListBO createPrizeList(PrizeListBO prizeListBO) throws PlayerNotFoundException, PrizeListAlreadyExistsException {
        PlayerEntity player = playerRepository.findById(prizeListBO.getPlayerEmail())
                .orElseThrow(() -> new PlayerNotFoundException("Player not found"));

        if(prizeListRepository.existsById(new PrizelistId(player.getEmail(), prizeListBO.getPrizeListName()))) {
            throw new PrizeListAlreadyExistsException("Tournament already exists");
        }

        PrizeListEntity prizeListEntity = prizeListEntityMapper.mapFromBO(prizeListBO);
        prizeListEntity.setPlayer(player);
        return prizeListEntityMapper.mapToBO(prizeListRepository.save(prizeListEntity));
    }

    @Override
    public List<PrizeListBO> getAllPrizeList() {
        return prizeListRepository.findAll().stream().map(prizeListEntityMapper::mapToBO).toList();
    }
}
