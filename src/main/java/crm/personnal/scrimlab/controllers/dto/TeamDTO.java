package crm.personnal.scrimlab.controllers.dto;

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
        Integer rankingPoints
) {
}
