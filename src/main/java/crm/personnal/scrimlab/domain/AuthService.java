package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseDTO register(PlayerDTO playerDTO) throws Exception;
}
