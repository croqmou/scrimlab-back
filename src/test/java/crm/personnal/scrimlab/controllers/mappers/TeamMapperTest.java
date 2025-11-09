package crm.personnal.scrimlab.controllers.mappers;

import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.bo.TeamBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class TeamMapperTest {
    @Mock
    private InputPlayerMapper inputPlayerMapper;

    @InjectMocks
    private TeamMapper teamMapper;

    @Nested
    class MapToBO{

        @Test
        void should_map_to_bo(){
            //GIVEN
            TeamDTO teamDTO = new TeamDTO("CRM ESPORT", "unknown.png", "unknown.png", "decsription", null,null,null,null,null,null,null,0,0,0,0);
            TeamBO expected = new TeamBO("CRM ESPORT", "unknown.png", "unknown.png", "decsription", null,null,null,null,null,null,null,0,0,0,0);
            //WHEN
            TeamBO actual = teamMapper.mapToBO(teamDTO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }

    @Nested
    class MapFromBO {

        @Test
        void should_map_from_bo() {
            //GIVEN
            TeamBO teamBO = new TeamBO("CRM ESPORT", "unknown.png", "unknown.png", "decsription", null, null, null, null, null, null, null, 0, 0, 0, 0);
            TeamDTO expected = new TeamDTO("CRM ESPORT", "unknown.png", "unknown.png", "decsription", null, null, null, null, null, null, null, 0, 0, 0, 0);
            //WHEN
            TeamDTO actual = teamMapper.mapFromBO(teamBO);
            //THEN
            assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        }
    }
}