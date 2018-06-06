package de.utini.digitalscrumboard.board.state;

import de.utini.digitalscrumboard.board.Board;
import de.utini.digitalscrumboard.board.story.task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="states")
public class State {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @OneToMany(mappedBy = "state")
    private List<Task> tasks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Updating State with the DTO.
     * @param dto The DTO of the State.
     */
    public void importDTO(StateDTO dto) {
        setName(dto.getName());
    }

    /**
     * Exporting the StateDTO of this State.
     * @return the StateDTO.
     */
    public StateDTO exportDTO() {
        return new StateDTO(id, name);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
