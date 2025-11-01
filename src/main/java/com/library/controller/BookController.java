package com.library.controller;

import com.library.dto.user.BookResponseDTO;
import com.library.dto.user.CreateBookRequestDTO;
import com.library.dto.user.UpdateBookRequestDTO;
import com.library.entities.BookEntity;
import com.library.mapper.BookMapper;
import com.library.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        List<BookEntity> bookEntities = bookService.findAll();

        List<BookResponseDTO> listBookResponse = new ArrayList<>();

        for (BookEntity bookEntity : bookEntities) {
            listBookResponse.add(BookMapper.toDTO(bookEntity));
        }

        return ResponseEntity.ok().body(listBookResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookResponseDTO> findById(@Valid @PathVariable Long id) {
        BookEntity bookEntity = bookService.findById(id);
        BookResponseDTO bookResponse = BookMapper.toDTO(bookEntity);

        return ResponseEntity.ok().body(bookResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@Valid @RequestBody CreateBookRequestDTO createBookRequestDTO) {
        BookEntity bookEntity = bookService.create(createBookRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
           .buildAndExpand(bookEntity.getId()).toUri();

        BookResponseDTO bookResponse = BookMapper.toDTO(bookEntity);

        return ResponseEntity.created(uri).body(bookResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BookResponseDTO> update(@Valid @PathVariable Long id, @Valid @RequestBody UpdateBookRequestDTO updateBook) {
        BookEntity bookEntity = bookService.update(id, updateBook);
        BookResponseDTO bookResponse = BookMapper.toDTO(bookEntity);

        return ResponseEntity.ok().body(bookResponse);
    }
}
