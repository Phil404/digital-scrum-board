package de.utini.digitalscrumboard.board.story.task;

import de.utini.digitalscrumboard.board.state.State;
import de.utini.digitalscrumboard.board.story.Story;
import de.utini.digitalscrumboard.user.User;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@Table(name = "tasks")
public class Task extends de.utini.digitalscrumboard.board.story.Issue {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignee;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private State state;

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

    public void importDTO(){

    }
    public void exportDTO(){

    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
