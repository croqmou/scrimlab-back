package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.PlayerMapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.AuthService;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final PlayerRepository playerRepository;
    private final PlayerEntityMapper playerEntityMapper;
    private final PlayerMapper playerMapper;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper, PlayerMapper playerMapper, JwtUtil jwtUtil) {
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
        this.playerMapper = playerMapper;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public AuthResponseDTO login(PlayerDTO playerDTO) throws Exception {
        PlayerEntity player = playerRepository.findByEmail(playerDTO.email());

        if (player == null || !new BCryptPasswordEncoder().matches(playerDTO.pwd(), player.getPwd())) {
            throw new Exception(); //TODO Faire une exception personnalisée
        }

        String token = jwtUtil.generateToken(playerDTO.email());
        return new AuthResponseDTO(token, playerDTO);
    }

    @Override
    public AuthResponseDTO register(PlayerDTO playerDTO) throws Exception {
        boolean playerAlreadyExists = playerRepository.existsById(playerDTO.email());

        if (playerAlreadyExists) {
            throw new Exception("Player already exists"); // TODO: créer une exception custom
        }

        // 👉 Hashage du mot de passe
        String hashedPassword = new BCryptPasswordEncoder().encode(playerDTO.pwd());

        // 👉 Mapper le DTO en BO, puis en entité
        PlayerBO playerBO = playerMapper.mapToBO(playerDTO);
        playerBO.setPwd(hashedPassword); // remplace le mot de passe en clair par le hashé

        playerRepository.save(playerEntityMapper.mapFromBO(playerBO));

        String token = jwtUtil.generateToken(playerDTO.email());
        return new AuthResponseDTO(token, playerDTO);
    }
}
