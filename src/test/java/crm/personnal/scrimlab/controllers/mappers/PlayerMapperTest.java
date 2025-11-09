package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerMapperTest {
    private final PlayerMapper playerMapper = new PlayerMapper();

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            PlayerDTO playerDTO = new PlayerDTO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            PlayerBO expected = new PlayerBO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            //WHEN
            PlayerBO actual = playerMapper.mapToBO(playerDTO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}