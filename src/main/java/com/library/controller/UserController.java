package com.library.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.library.dto.user.CreateUserRequestDTO;
import com.library.dto.user.UpdateUserRequestDTO;
import com.library.dto.user.UserResponseDTO;
import com.library.entities.UserEntity;
import com.library.mapper.UserMapper;
import com.library.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserEntity> userEntities = userService.findAll();

        List<UserResponseDTO> listUserResponse = new ArrayList<>();

        for (UserEntity userEntity : userEntities) {
            listUserResponse.add(UserMapper.toDTO(userEntity));
        }

        return ResponseEntity.ok().body(listUserResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@Valid @PathVariable Long id) {
        UserEntity userEntity = userService.findById(id);
        UserResponseDTO userResponse = UserMapper.toDTO(userEntity);

        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        UserEntity userEntity = userService.create(createUserRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
           .buildAndExpand(userEntity.getId()).toUri();

        UserResponseDTO userResponse = UserMapper.toDTO(userEntity);

        return ResponseEntity.created(uri).body(userResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody UpdateUserRequestDTO updateUser) {
        UserEntity userEntity = userService.update(id, updateUser);
        UserResponseDTO userResponse = UserMapper.toDTO(userEntity);

        return ResponseEntity.ok().body(userResponse);
    }
}
