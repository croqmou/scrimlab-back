package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.domain.FileUploadService;
import crm.personnal.scrimlab.exceptions.FileEmptyException;
import crm.personnal.scrimlab.exceptions.LoginOrPasswordIncorrectException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FileUploadControllerTest {
    @Mock
    private FileUploadService mockedFileUploadService;
    @InjectMocks
    private FileUploadController fileUploadController;


    @Nested
    class UploadTeamLogo {

        @Test
        void should_upload_team_logo_and_response_should_be_ok() throws LoginOrPasswordIncorrectException, FileEmptyException, IOException {
            //GIVEN
            MultipartFile file = mock(MultipartFile.class);
            Map<String,String> mockedMap = Map.of("file","file");
            doReturn(mockedMap).when(mockedFileUploadService).handleFileUploadTeamLogo(file);

            //WHEN
            ResponseEntity<Map<String,String>> response = fileUploadController.handleFileUploadTeamLogo(file);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            verify(mockedFileUploadService).handleFileUploadTeamLogo(file);
        }
    }

    @Nested
    class UploadTeamBanner {

        @Test
        void should_upload_team_Banner_and_response_should_be_ok() throws LoginOrPasswordIncorrectException, FileEmptyException, IOException {
            //GIVEN
            MultipartFile file = mock(MultipartFile.class);
            Map<String,String> mockedMap = Map.of("file","file");
            doReturn(mockedMap).when(mockedFileUploadService).handleFileUploadTeamBanner(file);

            //WHEN
            ResponseEntity<Map<String,String>> response = fileUploadController.handleFileUploadTeamBanner(file);

            //THEN
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            verify(mockedFileUploadService).handleFileUploadTeamBanner(file);
        }
    }
}
