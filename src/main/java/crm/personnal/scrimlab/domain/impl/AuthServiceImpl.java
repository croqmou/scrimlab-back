package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.external.OutputPlayerMapper;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
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
    private final InputPlayerMapper inputPlayerMapper;
    private final OutputPlayerMapper outputPlayerMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(TokenBlacklistService tokenBlacklistService, PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper, InputPlayerMapper inputPlayerMapper, OutputPlayerMapper outputPlayerMapper, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.tokenBlacklistService = tokenBlacklistService;
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
        this.inputPlayerMapper = inputPlayerMapper;
        this.outputPlayerMapper = outputPlayerMapper;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AuthResponseDTO login(InputPlayerDTO inputPlayerDTO) throws LoginOrPasswordIncorrectException {
        Optional<PlayerEntity> player = playerRepository.findByEmail(inputPlayerDTO.email());

        if (player.isEmpty() || !passwordEncoder.matches(inputPlayerDTO.pwd(), player.get().getPwd())) {
            throw new LoginOrPasswordIncorrectException("Email or password incorrect");
        }

        String token = jwtUtil.generateToken(inputPlayerDTO.email());
        return new AuthResponseDTO(token, outputPlayerMapper.mapFromBO(playerEntityMapper.mapToBO(player.get())));
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
    public AuthResponseDTO register(InputPlayerDTO inputPlayerDTO) throws PlayerAlreadyExistsException {
        boolean playerAlreadyExists = playerRepository.existsById(inputPlayerDTO.email());

        if (playerAlreadyExists) {
            throw new PlayerAlreadyExistsException("Player already exists");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(inputPlayerDTO.pwd());

        PlayerBO playerBO = inputPlayerMapper.mapToBO(inputPlayerDTO);
        playerBO.setPwd(hashedPassword);

        playerRepository.save(playerEntityMapper.mapFromBO(playerBO));

        String token = jwtUtil.generateToken(inputPlayerDTO.email());
        return new AuthResponseDTO(token, outputPlayerMapper.mapFromBO(playerBO));
    }
}
