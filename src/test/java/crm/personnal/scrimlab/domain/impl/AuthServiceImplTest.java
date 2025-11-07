package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.dto.AuthResponseDTO;
import crm.personnal.scrimlab.controllers.dto.PlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.PlayerMapper;
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
    private PlayerMapper mockedPlayerMapper;
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
            //GIVEN
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();
            PlayerDTO playerDTO = MockedData.mockedPlayerDTO();
            doReturn(Optional.of(playerEntity)).when(mockedPlayerRepository).findByEmail(anyString());
            doReturn("token").when(mockedJwtUtil).generateToken(anyString());
            doReturn(true).when(passwordEncoder).matches(anyString(),anyString());

            AuthResponseDTO authResponseDTO = MockedData.mockedAuthResponseDTO();
            //WHEN
            AuthResponseDTO expectedAuthResponseDTO = authService.login(playerDTO);
            //THEN
            assertThat(expectedAuthResponseDTO).usingRecursiveComparison()
                    .isEqualTo(authResponseDTO);
        }

        @Test
        void should_throw_login_or_password_incorrect_exception_if_player_no_exists() {
            //GIVEN
            doReturn(Optional.empty()).when(mockedPlayerRepository).findByEmail(anyString());
            PlayerDTO playerDTO = MockedData.mockedPlayerDTO();
            //WHEN THEN
            assertThrows(LoginOrPasswordIncorrectException.class, () -> authService.login(playerDTO));
        }

        @Test
        void should_throw_login_or_password_incorrect_exception_if_password_is_not_correct() {
            //GIVEN
            PlayerEntity playerEntity = MockedData.mockedPlayerEntity();
            playerEntity.setPwd("wrong password");
            doReturn(Optional.of(playerEntity)).when(mockedPlayerRepository).findByEmail(anyString());
            PlayerDTO playerDTO = MockedData.mockedPlayerDTO();
            //WHEN THEN
            assertThrows(LoginOrPasswordIncorrectException.class, () -> authService.login(playerDTO));
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
            //GIVEN
            PlayerDTO playerDTO = MockedData.mockedPlayerDTO();
            doReturn(false).when(mockedPlayerRepository).existsById(anyString());
            doReturn("token").when(mockedJwtUtil).generateToken(anyString());
            doReturn(MockedData.mockedPlayerBO()).when(mockedPlayerMapper).mapToBO(any(PlayerDTO.class));
            doReturn(MockedData.mockedPlayerEntity()).when(mockedPlayerEntityMapper).mapFromBO(any(PlayerBO.class));
            doReturn(MockedData.mockedPlayerEntity()).when(mockedPlayerRepository).save(any(PlayerEntity.class));

            AuthResponseDTO authResponseDTO = MockedData.mockedAuthResponseDTO();
            //WHEN
            AuthResponseDTO expectedAuthResponseDTO = authService.register(playerDTO);
            //THEN
            assertThat(expectedAuthResponseDTO).usingRecursiveComparison()
                    .isEqualTo(authResponseDTO);
            verify(mockedPlayerRepository).save(any(PlayerEntity.class));
        }

        @Test
        void should_throw_player_already_exists_exception_if_player_exists() {
            //GIVEN
            doReturn(true).when(mockedPlayerRepository).existsById(anyString());
            PlayerDTO playerDTO = MockedData.mockedPlayerDTO();
            //WHEN THEN
            assertThrows(PlayerAlreadyExistsException.class, () -> authService.register(playerDTO));
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

        public static PlayerDTO mockedPlayerDTO() {
            return new PlayerDTO(
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
                    mockedPlayerDTO()
            );
        }
    }

}