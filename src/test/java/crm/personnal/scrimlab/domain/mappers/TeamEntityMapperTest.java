package crm.personnal.scrimlab.domain.mappers;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TeamEntityMapperTest {
    @Mock
    private PlayerEntityMapper playerEntityMapper;

    @InjectMocks
    private TeamEntityMapper teamEntityMapper;

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            PlayerBO playerBO = new PlayerBO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            PlayerEntity playerEntity = getPlayerEntity("test@scrimlab.com", "CRM", "pwd", "unknown.png", false, 0,0,0,0,0,0);
            TeamBO teamBO = new TeamBO("CRM ESPORT", "unknown.png", "unknown.png", "decsription", playerBO,null,null,null,null,null,null,0,0,0,0);
            TeamEntity teamEntity = getTeamEntity("CRM ESPORT", "unknown.png", "unknown.png", "decsription", playerEntity,null,null,null,null,null,null,0,0,0,0);

            doReturn(playerBO).when(playerEntityMapper).mapToBO(any(PlayerEntity.class));

            //WHEN
            TeamBO actual = teamEntityMapper.mapToBO(teamEntity);

            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(teamBO);
        }

        private static TeamEntity getTeamEntity(String teamName, String teamLogo, String teamBanner, String teamDescription, PlayerEntity captain, PlayerEntity playerEntityTwo, PlayerEntity playerEntityThree, PlayerEntity sub, PlayerEntity secondSub, PlayerEntity coach, PlayerEntity manager, Integer rankingPoints, Integer teamGoals, Integer teamWins, Integer teamLoses) {
            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setTeamName(teamName);
            teamEntity.setTeamLogo(teamLogo);
            teamEntity.setTeamBanner(teamBanner);
            teamEntity.setTeamDescription(teamDescription);
            teamEntity.setCaptain(captain);
            teamEntity.setPlayerEntityTwo(playerEntityTwo);
            teamEntity.setPlayerEntityThree(playerEntityThree);
            teamEntity.setSub(sub);
            teamEntity.setSecondSub(secondSub);
            teamEntity.setCoach(coach);
            teamEntity.setManager(manager);
            teamEntity.setRankingPoints(rankingPoints);
            teamEntity.setTeamGoals(teamGoals);
            teamEntity.setTeamWins(teamWins);
            teamEntity.setTeamLoses(teamLoses);
            return teamEntity;
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
            TeamBO teamBO = new TeamBO("CRM ESPORT", "unknown.png", "unknown.png", "decsription", null,null,null,null,null,null,null,0,0,0,0);
            TeamEntity teamEntity = getTeamEntity("CRM ESPORT", "unknown.png", "unknown.png", "decsription", null,null,null,null,null,null,null,0,0,0,0);

            //WHEN
            TeamEntity actual = teamEntityMapper.mapFromBO(teamBO);

            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(teamEntity);
        }

        private static TeamEntity getTeamEntity(String teamName, String teamLogo, String teamBanner, String teamDescription, PlayerEntity captain, PlayerEntity playerEntityTwo, PlayerEntity playerEntityThree, PlayerEntity sub, PlayerEntity secondSub, PlayerEntity coach, PlayerEntity manager, Integer rankingPoints, Integer teamGoals, Integer teamWins, Integer teamLoses) {
            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setTeamName(teamName);
            teamEntity.setTeamLogo(teamLogo);
            teamEntity.setTeamBanner(teamBanner);
            teamEntity.setTeamDescription(teamDescription);
            teamEntity.setCaptain(captain);
            teamEntity.setPlayerEntityTwo(playerEntityTwo);
            teamEntity.setPlayerEntityThree(playerEntityThree);
            teamEntity.setSub(sub);
            teamEntity.setSecondSub(secondSub);
            teamEntity.setCoach(coach);
            teamEntity.setManager(manager);
            teamEntity.setRankingPoints(rankingPoints);
            teamEntity.setTeamGoals(teamGoals);
            teamEntity.setTeamWins(teamWins);
            teamEntity.setTeamLoses(teamLoses);
            return teamEntity;
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