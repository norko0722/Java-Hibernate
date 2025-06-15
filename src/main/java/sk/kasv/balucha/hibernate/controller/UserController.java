package sk.kasv.balucha.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kasv.balucha.hibernate.entities.User;
import sk.kasv.balucha.hibernate.service.UserService;
import sk.kasv.balucha.hibernate.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create new user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping(
            value = {"/{id}"},
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @Operation(
            summary = "Update user"
    )
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody @Valid UserDTO userDTO) {
        User user = this.userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (userDTO.getFirstName() != null && !userDTO.getFirstName().isBlank()) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null && !userDTO.getLastName().isBlank()) {
            user.setLastName(userDTO.getLastName());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().isBlank()) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getRole() != null && !userDTO.getRole().isBlank()) {
            user.setRole(userDTO.getRole());
        }

        User updatedUser = this.userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
} 