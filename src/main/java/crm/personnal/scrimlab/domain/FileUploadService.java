package crm.personnal.scrimlab.domain;

import crm.personnal.scrimlab.exceptions.FileEmptyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public interface FileUploadService {
    Map<String, String> handleFileUploadTeamLogo(MultipartFile file) throws IOException, FileEmptyException;
    Map<String, String> handleFileUploadTeamBanner(MultipartFile file) throws IOException, FileEmptyException;
}
