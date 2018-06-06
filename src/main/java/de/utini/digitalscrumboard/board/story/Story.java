package de.utini.digitalscrumboard.board.story;

import de.utini.digitalscrumboard.board.Board;
import de.utini.digitalscrumboard.board.story.task.Task;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="stories")
public class Story {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate createDate;

    private LocalDate doneDate;

    @OneToMany(mappedBy = "story")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDate doneDate) {
        this.doneDate = doneDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void importDTO(StoryDTO dto){
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.createDate = dto.getCreateDate();
        this.startDate = dto.getStartDate();
        this.doneDate = dto.getDoneDate();
    }

    public StoryDTO exportDTO(){
        return new StoryDTO(id, title, description, startDate, createDate, doneDate);
    }
}
