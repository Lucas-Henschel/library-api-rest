package com.library.controller;

import com.library.dto.author.AuthorResponseDTO;
import com.library.dto.author.CreateAuthorRequestDTO;
import com.library.dto.author.UpdateAuthorRequestDTO;
import com.library.entities.AuthorEntity;
import com.library.mapper.AuthorMapper;
import com.library.services.AuthorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> findAll() {
        List<AuthorEntity> authorEntities = authorService.findAll();

        List<AuthorResponseDTO> listAuthorResponse = new ArrayList<>();

        for (AuthorEntity authorEntity : authorEntities) {
            listAuthorResponse.add(AuthorMapper.toDTO(authorEntity));
        }

        return ResponseEntity.ok().body(listAuthorResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@Valid @PathVariable Long id) {
        AuthorEntity authorEntity = authorService.findById(id);
        AuthorResponseDTO authorResponse = AuthorMapper.toDTO(authorEntity);

        return ResponseEntity.ok().body(authorResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> create(@Valid @RequestBody CreateAuthorRequestDTO createAuthorRequestDTO) {
        AuthorEntity authorEntity = authorService.create(createAuthorRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
           .buildAndExpand(authorEntity.getId()).toUri();

        AuthorResponseDTO authorResponse = AuthorMapper.toDTO(authorEntity);

        return ResponseEntity.created(uri).body(authorResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AuthorResponseDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody UpdateAuthorRequestDTO updateAuthor) {
        AuthorEntity authorEntity = authorService.update(id, updateAuthor);
        AuthorResponseDTO authorResponse = AuthorMapper.toDTO(authorEntity);

        return ResponseEntity.ok().body(authorResponse);
    }
}
