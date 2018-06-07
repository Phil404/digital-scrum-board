package de.utini.digitalscrumboard.board.story.task;

import de.utini.digitalscrumboard.board.state.StateRepository;
import de.utini.digitalscrumboard.board.story.Story;
import de.utini.digitalscrumboard.board.story.StoryRepository;
import de.utini.digitalscrumboard.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private TaskRepository taskRepository;
    private StoryRepository storyRepository;
    private UserRepository userRepository;
    private StateRepository stateRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository,
                          StoryRepository storyRepository,
                          StateRepository stateRepository,
                          UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.storyRepository = storyRepository;
        this.stateRepository = stateRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/rest/boards/{boardId}/stories/{storyId}/tasks")
    public ResponseEntity getAllTasks(@PathVariable("boardId") Integer boardId,
                                      @PathVariable("storyId") Integer storyId) {
        return ResponseEntity.ok(storyRepository.getOne(storyId).getTasks().stream().map(Task::exportDTO).collect(Collectors.toList()));
    }

    @PostMapping("/rest/boards/{boardId}/stories/{storyId}/tasks")
    public ResponseEntity createTask(@PathVariable("boardId") Integer boardId,
                                     @PathVariable("storyId") Integer storyId,
                                     @RequestBody TaskDTO taskDTO) {
        Story story = storyRepository.getOne(storyId);

        Task task = new Task();
        task.importDTO(taskDTO);
        task.setStory(story);
        task.setAssignee(null);
        task.setState(null);

        story.getTasks().add(task);
        storyRepository.save(story);
        taskRepository.save(task);

        return ResponseEntity.created(URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + task.getId())
                .toUriString())
        ).build();
    }

    @GetMapping("/rest/boards/{boardId}/stories/{storyId}/tasks/{taskId}")
    public ResponseEntity getTask(@PathVariable("boardId") Integer boardId,
                                  @PathVariable("storyId") Integer storyId,
                                  @PathVariable("taskId") Integer taskId) {
        return ResponseEntity.ok(taskRepository.getOne(taskId).exportDTO());
    }

    @PutMapping("/rest/boards/{boardId}/stories/{storyId}/tasks/{taskId}")
    public ResponseEntity updateTask(@PathVariable("boardId") Integer boardId,
                                     @PathVariable("storyId") Integer storyId,
                                     @PathVariable("taskId") Integer taskId,
                                     @RequestBody TaskDTO taskDTO) {
        Task task = taskRepository.getOne(taskId);

        task.importDTO(taskDTO);


        if(taskDTO.getAssigneeID() != null)
            task.setAssignee(userRepository.getOne(taskDTO.getAssigneeID()));

        if(taskDTO.getStateID() != null)
            task.setState(stateRepository.getOne(taskDTO.getStateID()));

        taskRepository.save(task);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rest/boards/{boardId}/stories/{storyId}/tasks/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("boardId") Integer boardId,
                                     @PathVariable("storyId") Integer storyId,
                                     @PathVariable("taskId") Integer taskId) {
        taskRepository.deleteById(taskId);

        return ResponseEntity.noContent().build();
    }
}
