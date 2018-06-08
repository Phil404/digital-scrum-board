package de.utini.digitalscrumboard.board;

import de.utini.digitalscrumboard.board.state.State;
import de.utini.digitalscrumboard.board.story.Story;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;
    @OneToMany(mappedBy = "board")
    private List<Story> rows;
    @OneToMany(mappedBy = "board")
    private List<State> columns;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Story> getRows() {
        return rows;
    }

    public void setRows(List<Story> rows) {
        this.rows = rows;
    }

    public List<State> getColumns() {
        return columns;
    }

    public void setColumns(List<State> columns) {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void importDTO(BoardDTO dto) {
        this.name = dto.getName();
    }

    public BoardDTO exportDTO() {
        return new BoardDTO(id, name);
    }
}