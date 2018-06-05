package de.utini.digitalscrumboard.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String role;


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Import the Data from the DTO to this User.
     * @param dto The DTO of User.
     */
    public void importDTO(UserDTO dto) {
        this.name = dto.getName();
        this.role = dto.getRole();
    }

    /**
     * Export this User as DTO
     * @return The DTO of User.
     */
    public UserDTO exportDTO() {
        return new UserDTO(id, name, role);
    }
}
