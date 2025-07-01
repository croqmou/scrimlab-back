package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.TeamMapper;
import crm.personnal.scrimlab.domain.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;

    @PostMapping("/create")
    public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamDTO teamDTO) throws Exception {
        return ResponseEntity.ok(teamMapper.mapFromBO(
                teamService.addTeam(teamMapper.mapToBO(teamDTO))));
    }
}