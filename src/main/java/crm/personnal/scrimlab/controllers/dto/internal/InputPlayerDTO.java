package crm.personnal.scrimlab.controllers.dto.internal;

public record InputPlayerDTO(
        String username,
        String pwd,
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
