package crm.personnal.scrimlab.controllers;

import crm.personnal.scrimlab.controllers.dto.PrizeListDTO;
import crm.personnal.scrimlab.controllers.dto.TeamDTO;
import crm.personnal.scrimlab.controllers.mappers.PrizeListMapper;
import crm.personnal.scrimlab.controllers.mappers.TeamMapper;
import crm.personnal.scrimlab.domain.PlayerService;
import crm.personnal.scrimlab.domain.TeamService;
import crm.personnal.scrimlab.exceptions.CaptainNotFoundException;
import crm.personnal.scrimlab.exceptions.PlayerNotFoundException;
import crm.personnal.scrimlab.exceptions.PrizeListAlreadyExistsException;
import crm.personnal.scrimlab.exceptions.TeamAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PrizeListMapper prizeListMapper;

    @PostMapping("/prize-list")
    public ResponseEntity<PrizeListDTO> createPrizeList(@RequestBody PrizeListDTO prizeListDTO) throws PlayerNotFoundException, PrizeListAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(prizeListMapper.mapFromBO(
                playerService.createPrizeList(prizeListMapper.mapToBO(prizeListDTO))));
    }

    @GetMapping("/prize-list")
    public ResponseEntity<List<PrizeListDTO>> getAllPrizeList() {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.getAllPrizeList().stream().map(prizeListMapper::mapFromBO).toList());
    }
}