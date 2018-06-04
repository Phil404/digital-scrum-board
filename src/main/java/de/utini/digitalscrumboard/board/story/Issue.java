package de.utini.digitalscrumboard.board.story;
import de.utini.digitalscrumboard.board.story.task.Task;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Issue {

    int id;
    String title;
    String description;
    LinkedList<Task> relations;
    Date startDate;
    Date createDate;
    Date doneDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LinkedList<Task> getRelations() {
        return relations;
    }

    public void setRelations(LinkedList<Task> relations) {
        this.relations = relations;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }
}
