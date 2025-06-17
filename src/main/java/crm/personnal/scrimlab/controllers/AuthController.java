package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.domain.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PlayerDTO playerDTO) throws Exception {
        return ResponseEntity.ok(authService.login(playerDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody PlayerDTO playerDTO) throws Exception {
        return ResponseEntity.ok(authService.register(playerDTO));
    }
}