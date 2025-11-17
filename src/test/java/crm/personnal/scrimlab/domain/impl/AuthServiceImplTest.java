package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.external.OutputPlayerMapper;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.data.entities.PlayerEntity;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.bo.PlayerBO;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import crm.personnal.scrimlab.exceptions.PlayerAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private TokenBlacklistService mockedTokenBlacklistService;
    @Mock
    private PlayerRepository mockedPlayerRepository;
    @Mock
    private PlayerEntityMapper mockedPlayerEntityMapper;
    @Mock
    private OutputPlayerMapper mockedOutputPlayerMapper;
    @Mock
    private InputPlayerMapper mockedInputPlayerMapper;
    @Mock
    private JwtUtil mockedJwtUtil;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;


    @Nested
    class Login {

        @Test
        void should_login() throws LoginOrPasswordIncorrectException {
            // GIVEN
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();
            InputPlayerDTO inputPlayerDTO = MockedData.mockedPlayerDTO();

            doReturn(Optional.of(playerEntity))
                    .when(mockedPlayerRepository)
                    .findByEmail(anyString());

            doReturn(true)
                    .when(passwordEncoder)
                    .matches(anyString(), anyString());

            PlayerBO mockedBO = MockedData.mockedPlayerBO();
            doReturn(mockedBO)
                    .when(mockedPlayerEntityMapper)
                    .mapToBO(any(PlayerEntity.class));

            OutputPlayerDTO mockedOutputDTO = MockedData.mockedOutputPlayerDTO();
            doReturn(mockedOutputDTO)
                    .when(mockedOutputPlayerMapper)
                    .mapFromBO(any(PlayerBO.class));

            doReturn("token")
                    .when(mockedJwtUtil)
                    .generateToken(anyString());

            AuthResponseDTO expected = MockedData.mockedAuthResponseDTO();

            // WHEN
            AuthResponseDTO actual = authService.login(inputPlayerDTO);

            // THEN
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(expected);
        }


        @Test
        void should_throw_login_or_password_incorrect_exception_if_player_no_exists() {
            //GIVEN
            doReturn(Optional.empty()).when(mockedPlayerRepository).findByEmail(anyString());
            InputPlayerDTO inputPlayerDTO = MockedData.mockedPlayerDTO();
            //WHEN THEN
            assertThrows(LoginOrPasswordIncorrectException.class, () -> authService.login(inputPlayerDTO));
        }

        @Test
        void should_throw_login_or_password_incorrect_exception_if_password_is_not_correct() {
            //GIVEN
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();
            playerEntity.setPwd("wrong password");
            doReturn(Optional.of(playerEntity)).when(mockedPlayerRepository).findByEmail(anyString());
            InputPlayerDTO inputPlayerDTO = MockedData.mockedPlayerDTO();
            //WHEN THEN
            assertThrows(LoginOrPasswordIncorrectException.class, () -> authService.login(inputPlayerDTO));
        }
    }


    @Nested
    class Logout {

        @Test
        void should_logout() {
            //GIVEN
            when(request.getHeader("Authorization")).thenReturn("Bearer token123");
            doNothing().when(mockedTokenBlacklistService).blacklistToken(anyString());
            //WHEN
            authService.logout(request);
            //THEN
            verify(mockedTokenBlacklistService).blacklistToken(anyString());
        }
    }


    @Nested
    class Register {

        @Test
        void should_register() throws PlayerAlreadyExistsException {
            // GIVEN
            InputPlayerDTO inputPlayerDTO = MockedData.mockedPlayerDTO();

            doReturn(false).when(mockedPlayerRepository).existsById(anyString());
            doReturn("token").when(mockedJwtUtil).generateToken(anyString());

            PlayerBO mockedBO = MockedData.mockedPlayerBO();
            doReturn(mockedBO).when(mockedInputPlayerMapper).mapToBO(any(InputPlayerDTO.class));

            PlayerEntity mockedEntity = MockedData.mockedPlayerEntity();
            doReturn(mockedEntity).when(mockedPlayerEntityMapper).mapFromBO(any(PlayerBO.class));

            doReturn(mockedEntity).when(mockedPlayerRepository).save(any(PlayerEntity.class));

            OutputPlayerDTO mockedOutputDTO = MockedData.mockedOutputPlayerDTO();
            doReturn(mockedOutputDTO)
                    .when(mockedOutputPlayerMapper)
                    .mapFromBO(any(PlayerBO.class));

            AuthResponseDTO expectedAuthResponseDTO = MockedData.mockedAuthResponseDTO();

            // WHEN
            AuthResponseDTO actual = authService.register(inputPlayerDTO);

            // THEN
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(expectedAuthResponseDTO);

            verify(mockedPlayerRepository).save(any(PlayerEntity.class));
        }


        @Test
        void should_throw_player_already_exists_exception_if_player_exists() {
            //GIVEN
            doReturn(true).when(mockedPlayerRepository).existsById(anyString());
            InputPlayerDTO inputPlayerDTO = MockedData.mockedPlayerDTO();
            //WHEN THEN
            assertThrows(PlayerAlreadyExistsException.class, () -> authService.register(inputPlayerDTO));
        }
    }



    private class MockedData {
        public static PlayerEntity mockedPlayerEntity() {
            return new PlayerEntity(
                    "username",
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

        public static InputPlayerDTO mockedPlayerDTO() {
            return new InputPlayerDTO(
                    "username",
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

        public static OutputPlayerDTO mockedOutputPlayerDTO() {
            return new OutputPlayerDTO(
                    "username",
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

        public static PlayerBO mockedPlayerBO() {
            return new PlayerBO(
                    "username",
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

        public static AuthResponseDTO mockedAuthResponseDTO() {
            return new AuthResponseDTO(
                    "token",
                    mockedOutputPlayerDTO()
            );
        }
    }

}