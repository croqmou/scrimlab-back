package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.domain.AuthService;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // ✅ Injection par constructeur
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PlayerDTO playerDTO) throws LoginOrPasswordIncorrectException {
        return ResponseEntity.ok(authService.login(playerDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok().body("Logged out successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody PlayerDTO playerDTO) throws PlayerAlreadyExistsException {
        return ResponseEntity.ok(authService.register(playerDTO));
    }
}
