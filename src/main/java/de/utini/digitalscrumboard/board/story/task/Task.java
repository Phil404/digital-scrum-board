package de.utini.digitalscrumboard.board.story.task;

import de.utini.digitalscrumboard.board.state.State;
import de.utini.digitalscrumboard.board.story.Story;
import de.utini.digitalscrumboard.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;

@Entity
@Table(name = "tasks")
public class Task {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate createDate;

    private LocalDate doneDate;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

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

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void importDTO(TaskDTO dto){
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.startDate = dto.getStartDate();
        this.createDate = dto.getCreateDate();
        this.doneDate = dto.getDoneDate();
    }

    public TaskDTO exportDTO(){
        Integer assigneeId = assignee != null ? assignee.getId() : null;
        Integer stateId = state != null ? state.getId() : null;
        return new TaskDTO(assigneeId, stateId, id, title, description, startDate, createDate, doneDate);
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
