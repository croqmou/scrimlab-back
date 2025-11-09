package crm.personnal.scrimlab.domain.mappers;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerEntityMapperTest {
    private final PlayerEntityMapper playerEntityMapper;

    public PlayerEntityMapperTest() {
        this.playerEntityMapper = new PlayerEntityMapper();
    }

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            PlayerEntity playerEntity = getPlayerEntity("test@scrimlab.com", "CRM", "pwd", "unknown.png", false, 0,0,0,0,0,0);
            PlayerBO playerBO = new PlayerBO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);

            //WHEN
            PlayerBO actual = playerEntityMapper.mapToBO(playerEntity);

            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(playerBO);
        }

        private static PlayerEntity getPlayerEntity(String email, String username, String pwd, String pp, Boolean admin, Integer rankingPoints1s, Integer rankingPoints2s, Integer rankingPoints3s, Integer playerGoals, Integer playerWins, Integer playerLoses) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setUsername(username);
            playerEntity.setPwd(pwd);
            playerEntity.setEmail(email);
            playerEntity.setPp(pp);
            playerEntity.setAdmin(admin);
            playerEntity.setRankingPoints1s(rankingPoints1s);
            playerEntity.setRankingPoints2s(rankingPoints2s);
            playerEntity.setRankingPoints3s(rankingPoints3s);
            playerEntity.setPlayerGoals(playerGoals);
            playerEntity.setPlayerWins(playerWins);
            playerEntity.setPlayerLoses(playerLoses);
            return playerEntity;
        }

    }

    @Nested
    class MapFromBO {

        @Test
        void should_map_from_bo(){
            //GIVEN
            PlayerBO playerBO = new PlayerBO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            PlayerEntity playerEntity = getPlayerEntity("test@scrimlab.com", "CRM", "pwd", "unknown.png", false, 0,0,0,0,0,0);

            //WHEN
            PlayerEntity actual = playerEntityMapper.mapFromBO(playerBO);

            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(playerEntity);
        }

        private static PlayerEntity getPlayerEntity(String email, String username, String pwd, String pp, Boolean admin, Integer rankingPoints1s, Integer rankingPoints2s, Integer rankingPoints3s, Integer playerGoals, Integer playerWins, Integer playerLoses) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setUsername(username);
            playerEntity.setPwd(pwd);
            playerEntity.setEmail(email);
            playerEntity.setPp(pp);
            playerEntity.setAdmin(admin);
            playerEntity.setRankingPoints1s(rankingPoints1s);
            playerEntity.setRankingPoints2s(rankingPoints2s);
            playerEntity.setRankingPoints3s(rankingPoints3s);
            playerEntity.setPlayerGoals(playerGoals);
            playerEntity.setPlayerWins(playerWins);
            playerEntity.setPlayerLoses(playerLoses);
            return playerEntity;
        }

    }
}