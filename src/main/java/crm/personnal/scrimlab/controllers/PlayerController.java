package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.PlayerMapper;
import crm.personnal.scrimlab.domain.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private PlayerService playerService;
    private PlayerMapper playerMapper;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @PostMapping(path = "/add-player")
    public ResponseEntity<String> addPlayer() {
        return ResponseEntity.ok("Tu es authentifié !");

    }
}
