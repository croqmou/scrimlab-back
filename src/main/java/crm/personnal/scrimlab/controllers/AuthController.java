package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.PlayerMapper;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.AuthService;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerEntityMapper playerEntityMapper;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody PlayerDTO playerDTO) throws Exception {
        return ResponseEntity.ok(authService.register(playerDTO));
    }
}