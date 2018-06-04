package de.utini.digitalscrumboard.board.story.task;

public class Task extends de.utini.digitalscrumboard.board.story.Issue {
    Story story;
    List<User> assignee;
    State state;

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<User> getAssignee() {
        return assignee;
    }

    public void setAssignee(List<User> assignee) {
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
