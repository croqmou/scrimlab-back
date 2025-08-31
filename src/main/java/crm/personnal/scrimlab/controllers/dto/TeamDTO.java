package crm.personnal.scrimlab.controllers.dto;

import jakarta.persistence.Column;
import org.hibernate.annotations.ColumnDefault;

public record TeamDTO(
        String teamName,
        String teamLogo,
        String teamDescription,
        String captain,
        String playerEntityTwo,
        String playerEntityThree,
        String sub,
        String secondSub,
        String coach,
        String manager,
        Integer rankingPoints,
        Integer teamGoals,
        Integer teamWins,
        Integer teamLoses
) {
}
