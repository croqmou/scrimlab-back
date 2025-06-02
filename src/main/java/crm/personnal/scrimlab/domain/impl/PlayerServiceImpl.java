package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.PlayerService;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import crm.personnal.scrimlab.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;
    private PlayerEntityMapper playerEntityMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper) {
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
    }

    @Override
    public PlayerBO addPlayer(PlayerBO playerBO) {
        PlayerEntity res =  playerRepository.findByEmailAndPwd(
                playerBO.email(),
                playerBO.pwd()
        );

        if(res != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setUsername(playerBO.username());
        playerEntity.setPwd(playerBO.pwd());
        playerEntity.setEmail(playerBO.email());
        playerEntity.setPp(playerBO.pp());
        playerEntity.setAdmin(false);
        playerEntity.setRankingPoints1s(0);
        playerEntity.setRankingPoints2s(0);
        playerEntity.setRankingPoints3s(0);

        playerRepository.save(playerEntity);

        return playerEntityMapper.mapToBO(playerEntity);
    }
}
