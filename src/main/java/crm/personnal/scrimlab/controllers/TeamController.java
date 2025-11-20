package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.dto.TeamFullDTO;
import crm.personnal.scrimlab.controllers.dto.external.OutputPlayerDTO;
import crm.personnal.scrimlab.controllers.dto.internal.InputPlayerDTO;
import crm.personnal.scrimlab.controllers.mappers.TeamFullMapper;
import crm.personnal.scrimlab.controllers.mappers.TeamMapper;
import crm.personnal.scrimlab.controllers.mappers.external.OutputPlayerMapper;
import crm.personnal.scrimlab.controllers.mappers.internal.InputPlayerMapper;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import crm.personnal.scrimlab.exceptions.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamFullMapper teamFullMapper;

    @PostMapping("/create")
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) throws TeamAlreadyExistsException, CaptainNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMapper.mapFromBO(
                teamService.createTeam(teamMapper.mapToBO(teamDTO))));
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<Page<TeamDTO>> getAllTeams(Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllTeams(pageable).map(teamMapper::mapFromBO));
    }

    @GetMapping(path = "/{teamName}")
    public ResponseEntity<TeamFullDTO> getTeamByTeamName(@PathVariable String teamName) throws TeamNotFoundException {
        return ResponseEntity.ok(teamFullMapper.mapFromBO(teamService.getTeamByTeamName(teamName)));
    }

    @GetMapping(path = "/getAllByPlayer/{playerEmail}")
    public ResponseEntity<List<TeamDTO>> getAllTeamsByPlayer(@PathVariable String playerEmail) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                teamService.getAllTeamsByPlayer(playerEmail)
                        .stream()
                        .map(teamMapper::mapFromBO)
                        .toList()
        );
    }
}