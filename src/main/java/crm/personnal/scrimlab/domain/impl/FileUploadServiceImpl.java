package crm.personnal.scrimlab.domain.impl;

import crm.personnal.scrimlab.domain.FileUploadService;
import crm.personnal.scrimlab.exceptions.FileEmptyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private static final String UPLOAD_DIR = "../scrimlab-front/src/assets/img/teams-logos";

    @Override
    public Map<String, String> handleFileUpload(MultipartFile file) throws IOException, FileEmptyException {
        if (file.isEmpty()) {
            throw new FileEmptyException("File is empty");
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path uploadPath = Paths.get(UPLOAD_DIR);
        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return Map.of("filename", filename);
    }
}
