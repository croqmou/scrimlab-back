package crm.personnal.scrimlab.domain.mappers;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.PrizeListEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.data.entities.embadded.PrizelistId;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PrizeListEntityMapperTest {
    @InjectMocks
    private PrizeListEntityMapper prizeListEntityMapper;

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            PrizeListBO prizeListBO = new PrizeListBO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
            PlayerEntity playerEntity = getPlayerEntity("test@scrimlab.com", "CRM", "pwd", "unknown.png", false, 0,0,0,0,0,0);
            PrizeListEntity prizeListEntity = getPrizeListEntity(playerEntity, "RLCS", 1, LocalDate.of(2025,1,1));

            //WHEN
            PrizeListBO actual = prizeListEntityMapper.mapToBO(prizeListEntity);

            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(prizeListBO);
        }

        private static PrizeListEntity getPrizeListEntity(PlayerEntity player, String prizeListName, int result, LocalDate date) {
            PrizeListEntity prizeListEntity = new PrizeListEntity();
            prizeListEntity.setId(new PrizelistId(player.getEmail(), prizeListName));
            prizeListEntity.setPlayer(player);
            prizeListEntity.setResult(result);
            prizeListEntity.setTournamentDate(date);
            return prizeListEntity;
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
            PrizeListBO prizeListBO = new PrizeListBO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
            PlayerEntity playerEntity = getPlayerEntity("test@scrimlab.com", "CRM", "pwd", "unknown.png", false, 0,0,0,0,0,0);
            PrizeListEntity prizeListEntity = getPrizeListEntity(playerEntity, "RLCS", 1, LocalDate.of(2025,1,1));

            //WHEN
            PrizeListEntity actual = prizeListEntityMapper.mapFromBO(prizeListBO);
            prizeListEntity.setPlayer(null);

            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(prizeListEntity);
        }

        private static PrizeListEntity getPrizeListEntity(PlayerEntity player, String prizeListName, int result, LocalDate date) {
            PrizeListEntity prizeListEntity = new PrizeListEntity();
            prizeListEntity.setId(new PrizelistId(player.getEmail(), prizeListName));
            prizeListEntity.setPlayer(player);
            prizeListEntity.setResult(result);
            prizeListEntity.setTournamentDate(date);
            return prizeListEntity;
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