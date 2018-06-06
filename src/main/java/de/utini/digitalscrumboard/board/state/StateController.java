package de.utini.digitalscrumboard.board.state;

import de.utini.digitalscrumboard.board.Board;
import de.utini.digitalscrumboard.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class StateController {
    private StateRepository stateRepository;
    private BoardRepository boardRepository;

    @Autowired
    public StateController(StateRepository stateRepository, BoardRepository boardRepository){
        this.stateRepository = stateRepository;
        this.boardRepository = boardRepository;
    }

    @GetMapping("/rest/boards/{boardId}/states")
    public ResponseEntity getAllStates(@PathVariable("boardId") Integer boardId){
        List<StateDTO> stateDTOS = boardRepository.getOne(boardId).getColumns().stream().map(State::exportDTO).collect(Collectors.toList());
        return ResponseEntity.ok(stateDTOS);
    }

    @PostMapping("/rest/boards/{boardId}/states")
    public ResponseEntity createState(@PathVariable("boardId") Integer boardId,
                                      @RequestBody StateDTO stateDTO) {
        Board board = boardRepository.getOne(boardId);

        State state = new State();
        state.importDTO(stateDTO);
        state.setBoard(board);
        board.getColumns().add(state);
        boardRepository.save(board);
        stateRepository.save(state);

        return ResponseEntity.created(URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + state.getId())
                .toUriString()
            )).build();
    }

    @GetMapping("/rest/boards/{boardId}/states/{stateId}")
    public ResponseEntity getState(@PathVariable("boardId") Integer boardId,
                                   @PathVariable("stateId") Integer stateId) {
        return ResponseEntity.ok(stateRepository.getOne(stateId).exportDTO());
    }

    @PutMapping("/rest/boards/{boardId}/states/{stateId}")
    public ResponseEntity editState(@PathVariable("boardId") Integer boardId,
                                    @PathVariable("stateId") Integer stateId,
                                    @RequestBody StateDTO stateDTO) {
        State state = stateRepository.getOne(stateId);

        state.importDTO(stateDTO);

        stateRepository.save(state);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rest/boards/{boardId}/states/{stateId}")
    public ResponseEntity deleteState(@PathVariable("boardId") Integer boardId,
                                      @PathVariable("stateId") Integer stateId) {
        Board board = boardRepository.getOne(boardId);
        State state = stateRepository.getOne(stateId);

        board.getColumns().remove(state);

        boardRepository.save(board);
        stateRepository.delete(state);

        return ResponseEntity.noContent().build();
    }

}
