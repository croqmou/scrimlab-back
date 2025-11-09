package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.domain.AuthService;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private AuthService mockedAuthService;
    @InjectMocks
    private AuthController authController;


    @Nested
    class Login {

        @Test
        void should_login_and_response_should_be_ok() throws LoginOrPasswordIncorrectException {
            //GIVEN
            PlayerDTO mockedPlayerDTO = MockedData.mockedPlayerDTO();
            AuthResponseDTO mockedAuthResponseDTO = MockedData.mockedAuthResponseDTO();
            doReturn(mockedAuthResponseDTO).when(mockedAuthService).login(mockedPlayerDTO);

            //WHEN
            ResponseEntity<AuthResponseDTO> response = authController.login(mockedPlayerDTO);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            verify(mockedAuthService).login(mockedPlayerDTO);
        }
    }

    @Nested
    class Logout {

        @Test
        void should_logout_and_response_should_be_ok() {
            //GIVEN
            HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

            //WHEN
            ResponseEntity<?> response = authController.logout(mockedRequest);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            verify(mockedAuthService).logout(mockedRequest);
        }
    }

    @Nested
    class Register {

        @Test
        void should_register_and_response_should_be_ok() throws PlayerAlreadyExistsException {
            //GIVEN
            PlayerDTO mockedPlayerDTO = MockedData.mockedPlayerDTO();
            AuthResponseDTO mockedAuthResponseDTO = MockedData.mockedAuthResponseDTO();
            doReturn(mockedAuthResponseDTO).when(mockedAuthService).register(mockedPlayerDTO);

            //WHEN
            ResponseEntity<AuthResponseDTO> response = authController.register(mockedPlayerDTO);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            verify(mockedAuthService).register(mockedPlayerDTO);
        }
    }


    private class MockedData {
        public static PlayerDTO mockedPlayerDTO(){
            return new PlayerDTO(
                    "CRM",
                    "pwd",
                    "test@scrimlab.com",
                    "unknown.png",
                    false,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
            );
        }

        public static AuthResponseDTO mockedAuthResponseDTO(){
            return new AuthResponseDTO(
                    "token",
                    mockedPlayerDTO()
            );
        }
    }
}
