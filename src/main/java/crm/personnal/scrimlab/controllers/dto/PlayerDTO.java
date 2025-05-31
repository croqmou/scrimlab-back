package crm.personnal.scrimlab.controllers.dto;

public record PlayerDTO (
        String username,
        String pwd,
        String email,
        String pp,
        boolean admin,
        int rankingPoints1s,
        int rankingPoints2s,
        int rankingPoints3s
) {
}
