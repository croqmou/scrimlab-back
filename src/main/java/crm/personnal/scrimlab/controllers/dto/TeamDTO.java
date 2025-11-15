package crm.personnal.scrimlab.controllers.dto;

public record TeamDTO(
        String teamName,
        String teamLogo,
        String teamBanner,
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
