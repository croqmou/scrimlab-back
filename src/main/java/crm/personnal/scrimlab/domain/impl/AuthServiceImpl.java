package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.PlayerMapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.AuthService;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final TokenBlacklistService  tokenBlacklistService;
    private final PlayerRepository playerRepository;
    private final PlayerEntityMapper playerEntityMapper;
    private final PlayerMapper playerMapper;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(TokenBlacklistService tokenBlacklistService, PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper, PlayerMapper playerMapper, JwtUtil jwtUtil) {
        this.tokenBlacklistService = tokenBlacklistService;
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
        this.playerMapper = playerMapper;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public AuthResponseDTO login(PlayerDTO playerDTO) throws LoginOrPasswordIncorrectException {
        Optional<PlayerEntity> player = playerRepository.findByEmail(playerDTO.email());

        if (player.isEmpty() || !new BCryptPasswordEncoder().matches(playerDTO.pwd(), player.get().getPwd())) {
            throw new LoginOrPasswordIncorrectException("Login or password incorrect");
        }

        String token = jwtUtil.generateToken(playerDTO.email());
        return new AuthResponseDTO(token, playerDTO);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
        }
    }

    @Override
    public AuthResponseDTO register(PlayerDTO playerDTO) throws PlayerAlreadyExistsException {
        boolean playerAlreadyExists = playerRepository.existsById(playerDTO.email());

        if (playerAlreadyExists) {
            throw new PlayerAlreadyExistsException("Player already exists");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(playerDTO.pwd());

        PlayerBO playerBO = playerMapper.mapToBO(playerDTO);
        playerBO.setPwd(hashedPassword);

        playerRepository.save(playerEntityMapper.mapFromBO(playerBO));

        String token = jwtUtil.generateToken(playerDTO.email());
        return new AuthResponseDTO(token, playerDTO);
    }
}
