package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.PlayerMapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.AuthService;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
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
        boolean playerExists = playerRepository.existsByEmailAndPwd(playerDTO.email(), playerDTO.pwd());

        if (!playerExists) {
            throw new Exception(); //TODO Faire une exception personnalisée
        }

        String token = jwtUtil.generateToken(playerDTO.email());
        return new AuthResponseDTO(token, playerDTO);
    }

    @Override
    public AuthResponseDTO register(PlayerDTO playerDTO) throws Exception {
        boolean playerAlreadyExists = playerRepository.existsById(playerDTO.email());

        if (playerAlreadyExists) {
            throw new Exception(); //TODO Faire une exception personnalisée
        }

        playerRepository.save(playerEntityMapper.mapFromBO(playerMapper.mapToBO(playerDTO)));

        String token = jwtUtil.generateToken(playerDTO.email());
        return new AuthResponseDTO(token, playerDTO);
    }
}
