package com.lucianvaleanu.controller;

import com.lucianvaleanu.model.User;
import com.lucianvaleanu.service.UserService;
import com.lucianvaleanu.utils.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return userService.findUserById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(toEntity(userDTO));
        return ResponseEntity.ok(toDTO(createdUser));
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.id());
        user.setEmail(dto.email());
        user.setCreatedAt(dto.createdAt());
        return user;
    }
}