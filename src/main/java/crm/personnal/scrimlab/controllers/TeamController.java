package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.TeamMapper;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;

    @PostMapping("/create")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) throws TeamAlreadyExistsException, CaptainNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMapper.mapFromBO(
                teamService.createTeam(teamMapper.mapToBO(teamDTO))));
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<Page<TeamDTO>> getAllTeams(Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllTeams(pageable).map(teamMapper::mapFromBO));
    }
}