package crm.personnal.scrimlab.controllers.dto;

import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;

public class AuthResponseDTO {
    private String token;
    private OutputPlayerDTO player;

    public AuthResponseDTO(String token, OutputPlayerDTO player) {
        this.token = token;
        this.player = player;
    }

    public String getToken() {
        return token;
    }

    public OutputPlayerDTO getPlayer() {
        return player;
    }
}
