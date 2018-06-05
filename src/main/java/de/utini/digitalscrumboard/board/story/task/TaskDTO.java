package de.utini.digitalscrumboard.board.story.task;

import java.util.Date;
import java.util.LinkedList;

public class TaskDTO {

    int storyID;
    LinkedList<Integer> assignee;
    int state;
    int id;
    String title;
    String description;
    Date startDate;
    Date createDate;
    Date doneDate;

    public TaskDTO(int storyID, LinkedList<Integer> assignee, int state, int id, String title, String description, Date startDate, Date createDate, Date doneDate) {
        this.storyID = storyID;
        this.assignee = assignee;
        this.state = state;
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.createDate = createDate;
        this.doneDate = doneDate;
    }
}
