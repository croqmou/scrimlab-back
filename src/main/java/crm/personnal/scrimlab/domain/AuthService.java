package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseDTO login(PlayerDTO playerDTO) throws LoginOrPasswordIncorrectException;

    void logout(HttpServletRequest request);

    AuthResponseDTO register(PlayerDTO playerDTO) throws PlayerAlreadyExistsException;
}
