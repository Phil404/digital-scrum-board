package de.utini.digitalscrumboard.board.state;

public class State {
    private Integer id;
    private String name;

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
}
