package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.controllers.dto.PrizeListDTO;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.PrizeListBO;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class PrizeListMapperTest {
    @InjectMocks
    private PrizeListMapper prizeListMapper;

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            PrizeListDTO prizeListDTO = new PrizeListDTO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
            PrizeListBO expected = new PrizeListBO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
            //WHEN
            PrizeListBO actual = prizeListMapper.mapToBO(prizeListDTO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }

    @Nested
    class MapFromBO {

        @Test
        void should_map_from_bo() {
            //GIVEN
            PrizeListBO prizeListBO = new PrizeListBO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
            PrizeListDTO expected = new PrizeListDTO("test@scrimlab.com", "RLCS", 1, LocalDate.of(2025,1,1));
            //WHEN
            PrizeListDTO actual = prizeListMapper.mapFromBO(prizeListBO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}