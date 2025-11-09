package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseDTO login(InputPlayerDTO inputPlayerDTO) throws LoginOrPasswordIncorrectException;

    void logout(HttpServletRequest request);

    AuthResponseDTO register(InputPlayerDTO inputPlayerDTO) throws PlayerAlreadyExistsException;
}
