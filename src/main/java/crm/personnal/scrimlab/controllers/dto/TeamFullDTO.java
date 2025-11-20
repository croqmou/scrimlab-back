package crm.personnal.scrimlab.controllers.dto;

import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;

public record TeamFullDTO(
        String teamName,
        String teamLogo,
        String teamBanner,
        String teamDescription,
        OutputPlayerDTO captain,
        OutputPlayerDTO playerEntityTwo,
        OutputPlayerDTO playerEntityThree,
        OutputPlayerDTO sub,
        OutputPlayerDTO secondSub,
        OutputPlayerDTO coach,
        OutputPlayerDTO manager,
        Integer rankingPoints,
        Integer teamGoals,
        Integer teamWins,
        Integer teamLoses
) {
}
