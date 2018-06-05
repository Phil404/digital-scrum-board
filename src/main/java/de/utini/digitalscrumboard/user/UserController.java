package de.utini.digitalscrumboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserController {

    private
    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/rest/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/rest/users")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.importDTO(userDTO);
        userRepository.save(user);
        return ResponseEntity.created(
                URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + user.getId())
                        .toUriString()
                )).build();
    }

    @GetMapping("/rest/users/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(userRepository.getOne(userId).exportDTO());
    }

    @PutMapping("/rest/users/{userId}")
    public ResponseEntity updateUser(@PathVariable("userId") Integer userId,
                                     @RequestBody UserDTO userDTO) {
        User user = userRepository.getOne(userId);
        user.importDTO(userDTO);
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rest/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Integer userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
