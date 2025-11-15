package com.library.services;

import com.library.dto.book.CreateBookRequestDTO;
import com.library.dto.book.UpdateBookRequestDTO;
import com.library.entities.BookEntity;
import com.library.helpers.UpdateValueHelper;
import com.library.repositories.BookRepository;
import com.library.services.exceptions.DatabaseException;
import com.library.services.exceptions.ResourceNotFoundException;
import com.library.services.exceptions.UnprocessableEntityException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado"));
    }

    public void delete(Long id) {
        try {
            findById(id);
            bookRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public BookEntity update(Long id, UpdateBookRequestDTO updateBook) {
        BookEntity entity = findById(id);
        updateData(entity, updateBook);

        return bookRepository.save(entity);
    }

    private void updateData(BookEntity entity, UpdateBookRequestDTO updateBook) {
        UpdateValueHelper.updateIfNotNull(entity::setTitle, updateBook.getTitle());
        UpdateValueHelper.updateIfNotNull(entity::setDescription, updateBook.getDescription());
        UpdateValueHelper.updateIfNotNull(entity::setPrice, updateBook.getPrice());
        UpdateValueHelper.updateIfNotNull(entity::setPages, updateBook.getPages());
        UpdateValueHelper.updateIfNotNull(entity::setLanguage, updateBook.getLanguage());
    }

    public BookEntity create(@Valid CreateBookRequestDTO createBook) {
        Optional<BookEntity> findBookByTitle = bookRepository.findByTitle(createBook.getTitle());

        if (findBookByTitle.isPresent()) {
            throw new UnprocessableEntityException("Já existe um livro com esse título");
        }

        BookEntity book = new BookEntity();
        book.setTitle(createBook.getTitle());
        book.setDescription(createBook.getDescription());
        book.setPrice(createBook.getPrice());
        book.setPages(createBook.getPages());
        book.setLanguage(createBook.getLanguage());

        return bookRepository.save(book);
    }
}
