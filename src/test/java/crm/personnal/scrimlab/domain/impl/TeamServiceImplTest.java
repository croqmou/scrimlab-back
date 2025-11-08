package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.data.repositories.TeamRepository;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.domain.mappers.TeamEntityMapper;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {
    @Mock
    private TeamEntityMapper mockedTeamEntityMapper;

    @Mock
    private TeamRepository mockedTeamRepository;

    @Mock
    private PlayerRepository mockedPlayerRepository;

    @InjectMocks
    private TeamServiceImpl teamService;


    @Nested
    class GetTeams {

        @Test
        void should_return_all_teams() {
            //GIVEN
            TeamEntity team1 = MockedData.mockedTeamEntity1();
            TeamEntity team2 = MockedData.mockedTeamEntity2();
            TeamBO teamBO1 = MockedData.mockedTeamBO1();
            TeamBO teamBO2 = MockedData.mockedTeamBO2();

            List<TeamEntity> teamEntities = Arrays.asList(team1, team2);
            List<TeamBO> teamBOs = Arrays.asList(teamBO1, teamBO2);
            Pageable pageable = PageRequest.of(0, 10);
            Page<TeamEntity> pageEntities = new PageImpl<>(teamEntities, pageable, teamEntities.size());
            Page<TeamBO> pageBOs = new PageImpl<>(teamBOs, pageable, teamBOs.size());

            doReturn(pageEntities).when(mockedTeamRepository).findAll(pageable);
            when(mockedTeamEntityMapper.mapToBO(team1)).thenReturn(teamBO1);
            when(mockedTeamEntityMapper.mapToBO(team2)).thenReturn(teamBO2);
            //WHEN
            Page<TeamBO> expectedPageBOs = teamService.getAllTeams(pageable);
            //THEN
            assertThat(expectedPageBOs.getContent())
                    .usingRecursiveComparison()
                    .isEqualTo(Arrays.asList(teamBO1,teamBO2));

            verify(mockedTeamEntityMapper).mapToBO(team1);
            verify(mockedTeamEntityMapper).mapToBO(team2);
            verify(mockedTeamRepository).findAll(pageable);
            assertThat(expectedPageBOs).usingRecursiveComparison()
                    .isEqualTo(pageBOs);
        }
    }


    @Nested
    class CreateTeam {

        @Test
        void should_create_team() throws TeamAlreadyExistsException, CaptainNotFoundException {
            //GIVEN
            TeamBO teamBO1 = MockedData.mockedTeamBO1();
            TeamEntity team1 = MockedData.mockedTeamEntity1();
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();

            doReturn(false).when(mockedTeamRepository).existsById(anyString());
            doReturn(Optional.of(playerEntity)).when(mockedPlayerRepository).findById(anyString());
            doReturn(team1).when(mockedTeamEntityMapper).mapFromBO(any(TeamBO.class));
            doReturn(teamBO1).when(mockedTeamEntityMapper).mapToBO(any(TeamEntity.class));
            doReturn(MockedData.mockedTeamEntity1()).when(mockedTeamRepository).save(team1);
            //WHEN
            TeamBO expectedTeamBO = teamService.createTeam(teamBO1);
            //THEN
            verify(mockedTeamEntityMapper).mapToBO(any());
            verify(mockedTeamEntityMapper).mapFromBO(teamBO1);
            verify(mockedTeamRepository).save(team1);
            assertThat(expectedTeamBO).usingRecursiveComparison()
                    .isEqualTo(teamBO1);
        }

        @Test
        void should_throw_team_already_exists_exception_if_team_already_exists() {
            //GIVEN
            TeamBO teamBO1 = MockedData.mockedTeamBO1();
            doReturn(true).when(mockedTeamRepository).existsById(anyString());
            //WHEN THEN
            assertThrows(TeamAlreadyExistsException.class, () -> teamService.createTeam(teamBO1));
        }

        @Test
        void should_throw_captain_not_found_exception_if_captain_not_found() {
            //GIVEN
            TeamBO teamBO1 = MockedData.mockedTeamBO1();
            doReturn(false).when(mockedTeamRepository).existsById(anyString());
            doReturn(Optional.empty()).when(mockedPlayerRepository).findById(anyString());
            //WHEN THEN
            assertThrows(CaptainNotFoundException.class, () -> teamService.createTeam(teamBO1));
        }
    }



    private class MockedData {
        public static TeamEntity mockedTeamEntity1(){
            TeamEntity team1 = new TeamEntity();
            team1.setTeamName("Team Alpha");
            team1.setTeamLogo("alpha-logo.png");
            team1.setTeamBanner("alpha-banner.jpg");
            team1.setTeamDescription("Best team in the league");
            team1.setRankingPoints(1500);
            team1.setTeamGoals(100);
            team1.setTeamWins(25);
            team1.setTeamLoses(5);

            return team1;
        }

        public static TeamEntity mockedTeamEntity2(){
            TeamEntity team2 = new TeamEntity();
            team2.setTeamName("Team Beta");
            team2.setTeamLogo("beta-logo.png");
            team2.setTeamBanner("beta-banner.jpg");
            team2.setTeamDescription("Rising stars");
            team2.setRankingPoints(1200);
            team2.setTeamGoals(80);
            team2.setTeamWins(20);
            team2.setTeamLoses(10);

            return team2;
        }

        public static TeamBO mockedTeamBO1(){
            return new TeamBO(
                    "Team Alpha",
                    "alpha-logo.png",
                    "alpha-banner.jpg",
                    "Best team in the league",
                    mockedPlayerBO(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    1500,
                    100,
                    25,
                    5
            );
        }

        public static TeamBO mockedTeamBO2(){
            return new TeamBO(
                    "Team Beta",
                    "beta-logo.png",
                    "beta-banner.jpg",
                    "Rising stars",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    1200,
                    80,
                    20,
                    10
            );
        }

        public static PlayerEntity mockedPlayerEntity(){
            return new PlayerEntity(
                    "CRM",
                    "pwd",
                    "test@scrimlab.com",
                    "unknown.png",
                    false,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
            );
        }

        public static PlayerBO mockedPlayerBO(){
            return new PlayerBO(
                    "CRM",
                    "pwd",
                    "test@scrimlab.com",
                    "unknown.png",
                    false,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
            );
        }
    }

}