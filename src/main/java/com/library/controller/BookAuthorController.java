package com.library.controller;

import com.library.dto.author.AuthorResponseDTO;
import com.library.dto.book.BookResponseDTO;
import com.library.dto.bookAuthor.BookAuthorResponseDTO;
import com.library.dto.bookAuthor.CreateBookAuthorRequestDTO;
import com.library.entities.BookAuthorEntity;
import com.library.mapper.AuthorMapper;
import com.library.mapper.BookAuthorMapper;
import com.library.mapper.BookMapper;
import com.library.services.BookAuthorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/bookAuthor")
public class BookAuthorController {
    @Autowired
    private BookAuthorService bookAuthorService;

    @GetMapping
    public ResponseEntity<List<BookAuthorResponseDTO>> findAll() {
        List<BookAuthorEntity> bookAuthorEntities = bookAuthorService.findAll();

        List<BookAuthorResponseDTO> listBookAuthorResponse = new ArrayList<>();

        for (BookAuthorEntity bookAuthorEntity : bookAuthorEntities) {
            listBookAuthorResponse.add(BookAuthorMapper.toDTO(bookAuthorEntity));
        }

        return ResponseEntity.ok().body(listBookAuthorResponse);
    }

    @GetMapping(value = "/books/{authorId}")
    public ResponseEntity<List<BookResponseDTO>> findAllLinksByAuthorId(@Valid @PathVariable("authorId") Long authorId) {
        List<BookAuthorEntity> bookAuthorEntities = bookAuthorService.findAllLinksByAuthorId(authorId);

        List<BookResponseDTO> listBooks = new ArrayList<>();

        for (BookAuthorEntity bookAuthorEntity : bookAuthorEntities) {
            listBooks.add(BookMapper.toDTO(bookAuthorEntity.getBook()));
        }

        return ResponseEntity.ok().body(listBooks);
    }

    @GetMapping(value = "/authors/{bookId}")
    public ResponseEntity<List<AuthorResponseDTO>> findAllLinksByBookId(@Valid @PathVariable("bookId") Long bookId) {
        List<BookAuthorEntity> bookAuthorEntities = bookAuthorService.findAllLinksByBookId(bookId);

        List<AuthorResponseDTO> listAuthorsResponse = new ArrayList<>();

        for (BookAuthorEntity bookAuthorEntity : bookAuthorEntities) {
            listAuthorsResponse.add(AuthorMapper.toDTO(bookAuthorEntity.getAuthor()));
        }

        return ResponseEntity.ok().body(listAuthorsResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookAuthorResponseDTO> findById(@Valid @PathVariable Long id) {
        BookAuthorEntity bookAuthorEntity = bookAuthorService.findById(id);
        BookAuthorResponseDTO bookAuthorResponseDTO = BookAuthorMapper.toDTO(bookAuthorEntity);

        return ResponseEntity.ok().body(bookAuthorResponseDTO);
    }

    @GetMapping(value = "/findLink/author/{authorId}/book/{bookId}")
    public ResponseEntity<BookAuthorResponseDTO> findByBookIdAndAuthorId(@Valid @PathVariable("authorId") Long authorId, @Valid @PathVariable("bookId") Long bookId) {
        BookAuthorEntity bookAuthorEntity = bookAuthorService.findByBookIdAndAuthorId(bookId, authorId);
        BookAuthorResponseDTO bookAuthorResponseDTO = BookAuthorMapper.toDTO(bookAuthorEntity);

        return ResponseEntity.ok().body(bookAuthorResponseDTO);
    }

    @PostMapping
    public ResponseEntity<BookAuthorResponseDTO> create(@Valid @RequestBody CreateBookAuthorRequestDTO createBookAuthorRequest) {
        BookAuthorEntity bookAuthorEntity = bookAuthorService.create(createBookAuthorRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(bookAuthorEntity.getId()).toUri();

        BookAuthorResponseDTO bookAuthorResponse = BookAuthorMapper.toDTO(bookAuthorEntity);

        return ResponseEntity.created(uri).body(bookAuthorResponse);
    }

    @DeleteMapping(value = "/author/{authorId}/book/{bookId}")
    public ResponseEntity<Void> delete(@Valid @PathVariable("authorId") Long authorId, @Valid @PathVariable("bookId") Long bookId) {
        bookAuthorService.removeLink(authorId, bookId);
        return ResponseEntity.noContent().build();
    }
}
