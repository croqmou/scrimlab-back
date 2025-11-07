package crm.personnal.scrimlab.controllers.dto;

public class AuthResponseDTO {
    private String token;
    private PlayerDTO player;

    public AuthResponseDTO(String token, PlayerDTO player) {
        this.token = token;
        this.player = player;
    }

    public String getToken() {
        return token;
    }

    public PlayerDTO getPlayer() {
        return player;
    }
}
