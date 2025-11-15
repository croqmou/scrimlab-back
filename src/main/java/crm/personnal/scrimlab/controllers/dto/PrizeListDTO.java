package crm.personnal.scrimlab.controllers.dto;

import java.time.LocalDate;
import java.util.Date;

public record PrizeListDTO(
        String playerEmail,
        String prizeListName,
        int result,
        LocalDate tournamentDate
) {}
