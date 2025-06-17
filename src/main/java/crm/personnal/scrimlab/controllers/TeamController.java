package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.TeamMapper;
import crm.personnal.scrimlab.domain.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMapper teamMapper;

    @PostMapping("/create")
    public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamDTO teamDTO) throws Exception {
        System.out.println(teamDTO.toString());
        return ResponseEntity.ok(teamMapper.mapFromBO(
                teamService.addTeam(teamMapper.mapToBO(teamDTO))));
    }
}