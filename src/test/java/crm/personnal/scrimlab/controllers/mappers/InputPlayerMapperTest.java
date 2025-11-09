package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InputPlayerMapperTest {
    private final InputPlayerMapper inputPlayerMapper = new InputPlayerMapper();

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            InputPlayerDTO inputPlayerDTO = new InputPlayerDTO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            PlayerBO expected = new PlayerBO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            //WHEN
            PlayerBO actual = inputPlayerMapper.mapToBO(inputPlayerDTO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}