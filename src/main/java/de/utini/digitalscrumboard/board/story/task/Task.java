package de.utini.digitalscrumboard.board.story.task;

import de.utini.digitalscrumboard.board.state.State;
import de.utini.digitalscrumboard.board.story.Story;
import de.utini.digitalscrumboard.user.User;

import java.util.LinkedList;

public class Task extends de.utini.digitalscrumboard.board.story.Issue {
    Story story;
    LinkedList<User> assignee;
    State state;

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public LinkedList<User> getAssignee() {
        return assignee;
    }

    public void setAssignee(LinkedList<User> assignee) {
        this.assignee = assignee;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void importDTO{

    }
    public void exportDTO{

    }
}
