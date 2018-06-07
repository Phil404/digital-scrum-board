package de.utini.digitalscrumboard.board.story.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class TaskDTO {

    private Integer id;

    private String title;

    private String description;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate doneDate;

    private Integer assigneeID;

    private Integer stateID;

    public TaskDTO() {};

    public TaskDTO(Integer assigneeID,
                   Integer stateID,
                   Integer id,
                   String title,
                   String description,
                   LocalDate startDate,
                   LocalDate createDate,
                   LocalDate doneDate) {
        this.assigneeID = assigneeID;
        this.stateID = stateID;
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.createDate = createDate;
        this.doneDate = doneDate;
    }

    public Integer getAssigneeID() {
        return assigneeID;
    }

    public void setAssigneeID(Integer assigneeID) {
        this.assigneeID = assigneeID;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

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
}
