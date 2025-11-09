package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.TeamMapper;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {
    @Mock
    private TeamService mockedTeamService;
    @Mock
    private TeamMapper mockedTeamMapper;
    @InjectMocks
    private TeamController teamController;


    @Nested
    class CreateTeam {

        @Test
        void should_create_team_and_response_should_be_created() throws TeamAlreadyExistsException, CaptainNotFoundException {
            //GIVEN
            TeamDTO mockedTeamDTO = MockedData.mockedTeamDTO();
            TeamBO mockedTeamBO = MockedData.mockedTeamBO();
            doReturn(mockedTeamBO).when(mockedTeamMapper).mapToBO(mockedTeamDTO);
            doReturn(mockedTeamBO).when(mockedTeamService).createTeam(mockedTeamBO);
            doReturn(mockedTeamDTO).when(mockedTeamMapper).mapFromBO(mockedTeamBO);

            //WHEN
            ResponseEntity<TeamDTO> response = teamController.createTeam(mockedTeamDTO);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            verify(mockedTeamService).createTeam(mockedTeamBO);
        }
    }


    @Nested
    class GetAllTeams {

        @Test
        void should_get_all_teams_and_response_should_be_ok() {
            //GIVEN
            List<TeamBO> teamBOS = List.of(MockedData.mockedTeamBO(), MockedData.mockedTeamBO());

            Pageable pageable = PageRequest.of(0, 2);

            Page<TeamBO> page = new PageImpl<>(teamBOS, pageable, 2);

            Page<TeamDTO> expectedTeamDTOS = page
                    .map(teamBO -> new TeamDTO(
                            teamBO.getTeamName(),
                            teamBO.getTeamLogo(),
                            teamBO.getTeamBanner(),
                            teamBO.getTeamDescription(),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            teamBO.getRankingPoints(),
                            teamBO.getTeamGoals(),
                            teamBO.getTeamWins(),
                            teamBO.getTeamLoses()));

            doReturn(page).when(mockedTeamService).getAllTeams(PageRequest.of(0, 2));
            IntStream.range(0, teamBOS.size())
                    .forEach(index -> doReturn(expectedTeamDTOS.getContent().get(index)).when(mockedTeamMapper).mapFromBO(teamBOS.get(index)));

            //WHEN
            ResponseEntity<Page<TeamDTO>> response = teamController.getAllTeams(PageRequest.of(0, 2));

            //THEN
            assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(expectedTeamDTOS);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            verify(mockedTeamService).getAllTeams(PageRequest.of(0, 2));
            IntStream.range(0, teamBOS.size())
                    .forEach(index -> verify(mockedTeamMapper).mapFromBO(teamBOS.get(index)));
    }
}


    private class MockedData {
        public static TeamDTO mockedTeamDTO(){
            return new TeamDTO(
                    "Team Alpha",
                    "alpha-logo.png",
                    "alpha-banner.jpg",
                    "Best team in the league",
                    null,
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

        public static TeamBO mockedTeamBO(){
            return new TeamBO(
                    "Team Alpha",
                    "alpha-logo.png",
                    "alpha-banner.jpg",
                    "Best team in the league",
                    null,
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
                    "Team Alpha2",
                    "alpha-logo2.png",
                    "alpha-banner2.jpg",
                    "Best team in the league2",
                    null,
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
    }
}
