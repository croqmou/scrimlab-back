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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(path = "/add-player")
    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO) {
        return ResponseEntity.ok(playerMapper.mapFromBO(
                playerService.addPlayer(playerMapper.mapToBO(playerDTO))
        ));

    }
}
