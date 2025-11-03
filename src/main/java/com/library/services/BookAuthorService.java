package com.library.services;

import com.library.dto.bookAuthor.CreateBookAuthorRequestDTO;
import com.library.entities.AuthorEntity;
import com.library.entities.BookAuthorEntity;
import com.library.entities.BookEntity;
import com.library.repositories.BookAuthorRepository;

import com.library.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookAuthorService {
    @Autowired
    private BookAuthorRepository bookAuthorRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    public List<BookAuthorEntity> findAll() {
        return bookAuthorRepository.findAll();
    }

    public BookAuthorEntity findById(Long id) {
        Optional<BookAuthorEntity> bookAuthorEntity = bookAuthorRepository.findById(id);

        return bookAuthorEntity.orElseThrow(() -> new ResourceNotFoundException("Vínculo do livro e autor não encontrado"));
    }

    public BookAuthorEntity findByBookIdAndAuthorId(Long bookId, Long authorId) {
        bookService.findById(bookId);
        authorService.findById(authorId);

        Optional<BookAuthorEntity> bookAuthorEntity = bookAuthorRepository.findByAuthorIdAndBookId(authorId, bookId);

        return bookAuthorEntity.orElseThrow(() -> new ResourceNotFoundException("Vínculo do livro e autor não encontrado"));
    }

    public BookAuthorEntity create(CreateBookAuthorRequestDTO createBookAuthor) {
        BookEntity book = bookService.findById(createBookAuthor.getBookId());
        AuthorEntity author = authorService.findById(createBookAuthor.getAuthorId());

        BookAuthorEntity bookAuthorEntity = new BookAuthorEntity();
        bookAuthorEntity.setBook(book);
        bookAuthorEntity.setAuthor(author);

        return bookAuthorRepository.save(bookAuthorEntity);
    }

    public void removeLink(Long authorId, Long bookId) {
        bookService.findById(bookId);
        authorService.findById(authorId);

        bookAuthorRepository.deleteByBookIdAndAuthorId(bookId, authorId);
    }

    public List<BookAuthorEntity> findAllLinksByAuthorId(Long authorId) {
        authorService.findById(authorId);

        return bookAuthorRepository.findAllLinksByAuthorId(authorId);
    }

    public List<BookAuthorEntity> findAllLinksByBookId(Long bookId) {
        bookService.findById(bookId);

        return bookAuthorRepository.findAllLinksByBookId(bookId);
    }
}
