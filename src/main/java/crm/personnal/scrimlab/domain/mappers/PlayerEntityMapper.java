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
        newPlayerEntity.setUsername(bo.username());
        newPlayerEntity.setPwd(bo.pwd());
        newPlayerEntity.setEmail(bo.email());
        newPlayerEntity.setPp(bo.pp());
        newPlayerEntity.setAdmin(bo.admin());
        newPlayerEntity.setRankingPoints1s(bo.rankingPoints1s());
        newPlayerEntity.setRankingPoints2s(bo.rankingPoints2s());
        newPlayerEntity.setRankingPoints3s(bo.rankingPoints3s());

        return newPlayerEntity;
    }

}

