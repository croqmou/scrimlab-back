package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.domain.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    // ✅ Injection par constructeur
    public AuthController(AuthService authService, TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody PlayerDTO playerDTO) throws Exception {
        return ResponseEntity.ok(authService.login(playerDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }

        return ResponseEntity.ok().body("Logged out successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody PlayerDTO playerDTO) throws Exception {
        return ResponseEntity.ok(authService.register(playerDTO));
    }
}
