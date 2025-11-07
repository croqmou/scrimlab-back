package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.domain.FileUploadService;
import crm.personnal.scrimlab.exceptions.FileEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/files/upload")
public class FileUploadController {

    @Autowired
    public FileUploadService fileUploadService;

    @PostMapping("/team-logo")
    public ResponseEntity<Map<String, String>> handleFileUploadTeamLogo(@RequestParam("file") MultipartFile file) throws IOException, FileEmptyException { //TODO modifier le nom et le lien pour que ca soit juste pour les teams
        return ResponseEntity.ok(fileUploadService.handleFileUploadTeamLogo(file));
    }

    @PostMapping("/team-banner")
    public ResponseEntity<Map<String, String>> handleFileUploadTeamBanner(@RequestParam("file") MultipartFile file) throws IOException, FileEmptyException { //TODO modifier le nom et le lien pour que ca soit juste pour les teams
        return ResponseEntity.ok(fileUploadService.handleFileUploadTeamBanner(file));
    }
}
