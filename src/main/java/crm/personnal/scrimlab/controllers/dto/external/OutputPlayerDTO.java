package crm.personnal.scrimlab.controllers.dto.external;

public record OutputPlayerDTO(
        String username,
        String email,
        String pp,
        boolean admin,
        int playerGoals,
        int playerWins,
        int playerLoses,
        int rankingPoints1s,
        int rankingPoints2s,
        int rankingPoints3s
) {
}
