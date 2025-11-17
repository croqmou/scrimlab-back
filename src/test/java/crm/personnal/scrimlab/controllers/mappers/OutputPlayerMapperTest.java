package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.external.OutputPlayerMapper;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputPlayerMapperTest {
    private final OutputPlayerMapper outputPlayerMapper = new OutputPlayerMapper();

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            OutputPlayerDTO outputPlayerDTO = new OutputPlayerDTO("CRM", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            PlayerBO expected = new PlayerBO("CRM", null, "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            //WHEN
            PlayerBO actual = outputPlayerMapper.mapToBO(outputPlayerDTO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }

    @Nested
    class MapFromBO{

        @Test
        void should_map_from_bo(){
            //GIVEN
            PlayerBO playerBo = new PlayerBO("CRM", "pwd", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            OutputPlayerDTO expected = new OutputPlayerDTO("CRM", "test@scrimlab.com", "unknown.png", false, 0,0,0,0,0,0);
            //WHEN
            OutputPlayerDTO actual = outputPlayerMapper.mapFromBO(playerBo);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}