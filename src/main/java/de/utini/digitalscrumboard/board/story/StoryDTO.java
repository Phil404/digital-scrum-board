package de.utini.digitalscrumboard.board.story;

import java.util.Date;

public class StoryDTO {
    int id;
    String title;
    String description;
    Date startDate;
    Date createDate;
    Date doneDate;

    public StoryDTO(int id, String title, String description, Date startDate, Date createDate, Date doneDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.createDate = createDate;
        this.doneDate = doneDate;
    }
}
