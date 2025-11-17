package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.entities.PrizeListEntity;
import crm.personnal.scrimlab.data.entities.TeamEntity;
import crm.personnal.scrimlab.data.entities.embadded.PrizelistId;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.data.repositories.PrizeListRepository;
import crm.personnal.scrimlab.data.repositories.TeamRepository;
import crm.personnal.scrimlab.domain.PlayerService;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import crm.personnal.scrimlab.domain.mappers.PrizeListEntityMapper;
import crm.personnal.scrimlab.domain.mappers.TeamEntityMapper;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {
    @Mock
    private PrizeListEntityMapper mockedPrizeListEntityMapper;

    @Mock
    private PlayerRepository mockedPlayerRepository;

    @Mock
    private PrizeListRepository mockedPrizeListRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;


    @Nested
    class GetPrizeList {

        @Test
        void should_return_all_prize_lists() {
            //GIVEN
            PrizeListEntity prizeListEntity1 = MockedData.mockedPrizeListEntity1();
            PrizeListEntity prizeListEntity2 = MockedData.mockedPrizeListEntity2();
            PrizeListBO prizeListBO1 = MockedData.mockedPrizeListBO1();
            PrizeListBO prizeListBO2 = MockedData.mockedPrizeListBO2();

            List<PrizeListEntity> prizeListEntities = Arrays.asList(prizeListEntity1, prizeListEntity2);
            List<PrizeListBO> prizeListBOs = Arrays.asList(prizeListBO1, prizeListBO2);

            doReturn(prizeListEntities).when(mockedPrizeListRepository).findAll();
            when(mockedPrizeListEntityMapper.mapToBO(prizeListEntity1)).thenReturn(prizeListBO1);
            when(mockedPrizeListEntityMapper.mapToBO(prizeListEntity2)).thenReturn(prizeListBO2);
            //WHEN
            List<PrizeListBO> expectedBOs = playerService.getAllPrizeList();
            //THEN
            assertThat(expectedBOs)
                    .usingRecursiveComparison()
                    .isEqualTo(Arrays.asList(prizeListBO1,prizeListBO2));

            verify(mockedPrizeListEntityMapper).mapToBO(prizeListEntity1);
            verify(mockedPrizeListEntityMapper).mapToBO(prizeListEntity2);
            verify(mockedPrizeListRepository).findAll();
            assertThat(expectedBOs).usingRecursiveComparison()
                    .isEqualTo(prizeListBOs);
        }
    }


    @Nested
    class CreatePrizeList {

        @Test
        void should_create_prize_list() throws PlayerNotFoundException, PrizeListAlreadyExistsException {
            //GIVEN
            PrizeListBO prizeListBO = MockedData.mockedPrizeListBO1();
            PrizeListEntity prizeListEntity = MockedData.mockedPrizeListEntity1();
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();


            doReturn(Optional.of(playerEntity)).when(mockedPlayerRepository).findById(anyString());
            doReturn(false).when(mockedPrizeListRepository).existsById(any());
            doReturn(prizeListEntity).when(mockedPrizeListEntityMapper).mapFromBO(any(PrizeListBO.class));
            doReturn(prizeListBO).when(mockedPrizeListEntityMapper).mapToBO(any(PrizeListEntity.class));
            doReturn(prizeListEntity).when(mockedPrizeListRepository).save(prizeListEntity);
            //WHEN
            PrizeListBO expectedPrizeListBO = playerService.createPrizeList(prizeListBO);
            //THEN
            verify(mockedPrizeListEntityMapper).mapToBO(any());
            verify(mockedPrizeListEntityMapper).mapFromBO(prizeListBO);
            verify(mockedPrizeListRepository).save(prizeListEntity);
            verify(mockedPlayerRepository).findById(anyString());
            verify(mockedPrizeListRepository).existsById(any());
            assertThat(expectedPrizeListBO)
                    .isEqualTo(prizeListBO);
        }

        @Test
        void should_throw_player_not_found_exception() {
            //GIVEN
            PrizeListBO prizeListBO = MockedData.mockedPrizeListBO1();
            doReturn(Optional.empty()).when(mockedPlayerRepository).findById(anyString());
            //WHEN THEN
            assertThrows(PlayerNotFoundException.class, () -> playerService.createPrizeList(prizeListBO));
        }

        @Test
        void should_throw_prize_list_already_exists_exception() {
            //GIVEN
            PrizeListBO prizeListBO = MockedData.mockedPrizeListBO1();
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();

            doReturn(Optional.of(playerEntity)).when(mockedPlayerRepository).findById(anyString());
            doReturn(true).when(mockedPrizeListRepository).existsById(any());
            //WHEN THEN
            assertThrows(PrizeListAlreadyExistsException.class, () -> playerService.createPrizeList(prizeListBO));
        }
    }



    private class MockedData {
        public static PrizeListEntity mockedPrizeListEntity1(){
            PrizeListEntity prizeListEntity = new PrizeListEntity();
            prizeListEntity.setPlayer(mockedPlayerEntity());
            prizeListEntity.setId(new PrizelistId("test@scrimlab.com", "RLCS"));
            prizeListEntity.setResult(1);
            prizeListEntity.setTournamentDate(LocalDate.of(2025,1,1));

            return prizeListEntity;
        }

        public static PrizeListEntity mockedPrizeListEntity2(){
            PrizeListEntity prizeListEntity = new PrizeListEntity();
            prizeListEntity.setPlayer(mockedPlayerEntity());
            prizeListEntity.setId(new PrizelistId("test@scrimlab.com", "Gamer Assembly"));
            prizeListEntity.setResult(2);
            prizeListEntity.setTournamentDate(LocalDate.of(2025,2,2));

            return prizeListEntity;
        }

        public static PrizeListBO mockedPrizeListBO1(){
            return new PrizeListBO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
        }

        public static PrizeListBO mockedPrizeListBO2(){
            return new PrizeListBO("test@scrimlab.com", "Gamer Assembly", 2, LocalDate.of(2025,2,2));

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