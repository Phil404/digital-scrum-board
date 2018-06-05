package de.utini.digitalscrumboard.board.story;

import de.utini.digitalscrumboard.board.Board;
import de.utini.digitalscrumboard.board.story.task.Task;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="stories")
public class Story extends Issue {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @OneToMany(mappedBy = "story")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void importDTO(){

    }

    public void exportDTO(){

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
}
