package de.utini.digitalscrumboard.board.story;

import de.utini.digitalscrumboard.board.Board;
import de.utini.digitalscrumboard.board.BoardDTO;
import de.utini.digitalscrumboard.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class StoryController {

    private BoardRepository boardRepository;
    private StoryRepository storyRepository;

    @Autowired
    public StoryController(BoardRepository boardRepository, StoryRepository storyRepository) {
        this.boardRepository = boardRepository;
        this.storyRepository = storyRepository;
    }

    @GetMapping("/rest/boards/{boardId}/stories")
    public ResponseEntity getAllStories(@PathVariable("boardId") Integer boardId) {
        return ResponseEntity.ok(boardRepository.getOne(boardId).getRows().stream().map(Story::exportDTO).collect(Collectors.toList()));
    }

    @PostMapping("/rest/boards/{boardId}/stories")
    public ResponseEntity createStory(@PathVariable("boardId") Integer boardId,
                                      @RequestBody StoryDTO storyDTO) {
        Board board = boardRepository.getOne(boardId);
        Story story = new Story();

        story.importDTO(storyDTO);
        story.setBoard(board);
        storyRepository.save(story);

        return ResponseEntity.created(URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + story.getId())
                .toUriString())
        ).build();
    }

    @GetMapping("/rest/boards/{boardId}/stories/{storyId}")
    public ResponseEntity getStory(@PathVariable("boardId") Integer boardId,
                                   @PathVariable("storyId") Integer storyId) {
        return ResponseEntity.ok(storyRepository.getOne(storyId).exportDTO());
    }

    @PutMapping("/rest/boards/{boardId}/stories/{storyId}")
    public ResponseEntity updateStory(@PathVariable("boardId") Integer boardId,
                                      @PathVariable("storyId") Integer storyId,
                                      @RequestBody StoryDTO storyDTO) {
        Story story = storyRepository.getOne(storyId);
        story.importDTO(storyDTO);
        storyRepository.save(story);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rest/boards/{boardId}/stories/{storyId}")
    public ResponseEntity deleteStory(@PathVariable("boardId") Integer boardId,
                                      @PathVariable("storyId") Integer storyId) {
        storyRepository.deleteById(storyId);

        return ResponseEntity.noContent().build();
    }
}
