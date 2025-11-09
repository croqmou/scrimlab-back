package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.config.domain.TokenBlacklistService;
import crm.personnal.scrimlab.config.utils.JwtUtil;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.data.repositories.PlayerRepository;
import crm.personnal.scrimlab.domain.mappers.PlayerEntityMapper;
import crm.personnal.scrimlab.exceptions.FileEmptyException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class FileUploadServiceImplTest {
    @Mock
    private TokenBlacklistService  mockedTokenBlacklistService;
    @Mock
    private PlayerRepository mockedPlayerRepository;
    @Mock
    private PlayerEntityMapper mockedPlayerEntityMapper;
    @Mock
    private InputPlayerMapper mockedInputPlayerMapper;
    @Mock
    private JwtUtil mockedJwtUtil;
    @Mock
    private BCryptPasswordEncoder mockedPasswordEncoder;

    @InjectMocks
    private FileUploadServiceImpl fileUploadService;


    @Nested
    class teamLogo {

        @Test
        void should_upload_team_logo() throws IOException, FileEmptyException {
            //GIVEN
            MockMultipartFile file = new MockMultipartFile(
                    "file",
                    "test.txt",
                    "text/plain",
                    "Hello World".getBytes()
            );
            UUID fixedUUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

            try (MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
                mockedUUID.when(UUID::randomUUID).thenReturn(fixedUUID);

                //WHEN
                Map<String, String> expectedMap = fileUploadService.handleFileUploadTeamLogo(file);

                //THEN
                assertThat(expectedMap).usingRecursiveComparison()
                        .isEqualTo(Map.of("filename", fixedUUID + "_test.txt"));
            }
        }

        @Test
        void should_throw_file_empty_exception() {
            //GIVEN
            MockMultipartFile file = new MockMultipartFile(
                    "file",
                    "test.txt",
                    "text/plain",
                    new byte[]{}  // tableau vide
            );
            //WHEN THEN
            assertThrows(FileEmptyException.class, () -> fileUploadService.handleFileUploadTeamLogo(file));
        }

    }

    @Nested
    class teamBanner {

        @Test
        void should_upload_team_banner() throws IOException, FileEmptyException {
            //GIVEN
            MockMultipartFile file = new MockMultipartFile(
                    "file",
                    "test.txt",
                    "text/plain",
                    "Hello World".getBytes()
            );
            UUID fixedUUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

            try (MockedStatic<UUID> mockedUUID = mockStatic(UUID.class)) {
                mockedUUID.when(UUID::randomUUID).thenReturn(fixedUUID);

                //WHEN
                Map<String, String> expectedMap = fileUploadService.handleFileUploadTeamBanner(file);

                //THEN
                assertThat(expectedMap).usingRecursiveComparison()
                        .isEqualTo(Map.of("filename", fixedUUID + "_test.txt"));
            }
        }

        @Test
        void should_throw_file_empty_exception() {
            //GIVEN
            MockMultipartFile file = new MockMultipartFile(
                    "file",
                    "test.txt",
                    "text/plain",
                    new byte[]{}  // tableau vide
            );
            //WHEN THEN
            assertThrows(FileEmptyException.class, () -> fileUploadService.handleFileUploadTeamBanner(file));
        }

    }
}