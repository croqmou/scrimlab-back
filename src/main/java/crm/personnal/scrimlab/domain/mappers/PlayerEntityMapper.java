package crm.personnal.scrimlab.domain.mappers;

import crm.personnal.scrimlab.config.domain.Mapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.springframework.stereotype.Component;

@Component
public class PlayerEntityMapper implements Mapper<PlayerEntity, PlayerBO> {

    @Override
    public PlayerBO mapToBO(PlayerEntity userEntity) {
        return new PlayerBO(
                userEntity.getUsername(),
                userEntity.getPwd(),
                userEntity.getEmail(),
                userEntity.getPp(),
                userEntity.getAdmin(),
                userEntity.getRankingPoints1s(),
                userEntity.getRankingPoints2s(),
                userEntity.getRankingPoints3s()
        );
    }

    @Override
    public PlayerEntity mapFromBO(PlayerBO bo) {
        PlayerEntity newPlayerEntity = new PlayerEntity();
        newPlayerEntity.setUsername(bo.getUsername());
        newPlayerEntity.setPwd(bo.getPwd());
        newPlayerEntity.setEmail(bo.getEmail());
        newPlayerEntity.setPp(bo.getPp());
        newPlayerEntity.setAdmin(bo.isAdmin());
        newPlayerEntity.setRankingPoints1s(bo.getRankingPoints1s());
        newPlayerEntity.setRankingPoints2s(bo.getRankingPoints2s());
        newPlayerEntity.setRankingPoints3s(bo.getRankingPoints3s());

        return newPlayerEntity;
    }

}

