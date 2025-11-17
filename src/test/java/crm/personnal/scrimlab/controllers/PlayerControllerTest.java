package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.PrizeListDTO;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.PrizeListMapper;
import crm.personnal.scrimlab.domain.PlayerService;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.PlayerNotFoundException;
import crm.personnal.scrimlab.exceptions.PrizeListAlreadyExistsException;
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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {
    @Mock
    private PlayerService mockedPlayerService;
    @Mock
    private PrizeListMapper mockedPrizeListMapper;
    @InjectMocks
    private PlayerController playerController;


    @Nested
    class CreateTeam {

        @Test
        void should_create_prize_list_and_response_should_be_created() throws PlayerNotFoundException, PrizeListAlreadyExistsException {
            //GIVEN
            PrizeListDTO mockedPrizeListDTO = MockedData.mockedPrizeListDTO();
            PrizeListBO mockedPrizeListBO = MockedData.mockedPrizeListBO();
            doReturn(mockedPrizeListBO).when(mockedPrizeListMapper).mapToBO(mockedPrizeListDTO);
            doReturn(mockedPrizeListBO).when(mockedPlayerService).createPrizeList(mockedPrizeListBO);
            doReturn(mockedPrizeListDTO).when(mockedPrizeListMapper).mapFromBO(mockedPrizeListBO);

            //WHEN
            ResponseEntity<PrizeListDTO> response = playerController.createPrizeList(mockedPrizeListDTO);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            verify(mockedPlayerService).createPrizeList(mockedPrizeListBO);
        }
    }


    @Nested
    class GetAllTeams {

        @Test
        void should_get_all_teams_and_response_should_be_created() {
            //GIVEN
            List<PrizeListBO> prizeListBOS = List.of(MockedData.mockedPrizeListBO(), MockedData.mockedPrizeListBO());

            List<PrizeListDTO> expectedPrizeListDTOS = List.of(MockedData.mockedPrizeListDTO(),  MockedData.mockedPrizeListDTO());

            doReturn(prizeListBOS).when(mockedPlayerService).getAllPrizeList();
            IntStream.range(0, prizeListBOS.size())
                    .forEach(index -> doReturn(expectedPrizeListDTOS.get(index)).when(mockedPrizeListMapper).mapFromBO(prizeListBOS.get(index)));

            //WHEN
            ResponseEntity<List<PrizeListDTO>> response = playerController.getAllPrizeList();

            //THEN
            assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(expectedPrizeListDTOS);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            verify(mockedPlayerService).getAllPrizeList();
            IntStream.range(0, prizeListBOS.size())
                    .forEach(index -> verify(mockedPrizeListMapper).mapFromBO(prizeListBOS.get(index)));
        }
}


    private class MockedData {
        public static PrizeListDTO mockedPrizeListDTO(){
            return new PrizeListDTO(
                    "test@scrimlab.com",
                    "RLCS",
                    1,
                    LocalDate.of(2025,1,1)
            );
        }

        public static PrizeListBO mockedPrizeListBO(){
            return new PrizeListBO(
                    "test@scrimlab.com",
                    "RLCS",
                    1,
                    LocalDate.of(2025,1,1)
            );
        }
    }
}
