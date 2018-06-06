package de.utini.digitalscrumboard.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
public class BoardController {

    private BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/rest/boards")
    public ResponseEntity getAllBoards() {
        return ResponseEntity.ok(boardRepository.findAll().stream().map(Board::exportDTO).collect(Collectors.toList()));
    }

    @PostMapping("/rest/boards")
    public ResponseEntity createBoard(@RequestBody BoardDTO boardDTO) {
        Board board = new Board();

        board.importDTO(boardDTO);
        boardRepository.save(board);

        return ResponseEntity.created(URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + board.getId())
                .toUriString())).build();
    }

    @GetMapping("/rest/boards/{boardId}")
    public ResponseEntity getBoard(@PathVariable("boardId") Integer boardId) {
        return ResponseEntity.ok(boardRepository.getOne(boardId).exportDTO());
    }

    @PutMapping("/rest/boards/{boardId}")
    public ResponseEntity updateBoard(@PathVariable("boardId") Integer boardId,
                                      @RequestBody BoardDTO boardDTO) {
        Board board = boardRepository.getOne(boardId);

        board.importDTO(boardDTO);

        boardRepository.save(board);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rest/boards/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable("boardId") Integer boardId) {
        // TODO: Löschstrategie...
        boardRepository.deleteById(boardId);

        return ResponseEntity.noContent().build();
    }
}
