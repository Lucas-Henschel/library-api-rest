package com.library.services;

import com.library.dto.author.CreateAuthorRequestDTO;
import com.library.dto.author.UpdateAuthorRequestDTO;
import com.library.entities.AuthorEntity;
import com.library.helpers.DateHelper;
import com.library.helpers.UpdateValue;
import com.library.repositories.AuthorRepository;
import com.library.services.exceptions.DatabaseException;
import com.library.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorEntity> findAll() {
        return authorRepository.findAll();
    }

    public AuthorEntity findById(Long id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        return author.orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado"));
    }

    public void delete(Long id) {
        try {
            findById(id);
            authorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public AuthorEntity create(CreateAuthorRequestDTO createAuthor) {
        AuthorEntity author = new AuthorEntity();
        author.setName(createAuthor.getName());
        author.setBirthDate(DateHelper.dateStringToLocalDateTime(createAuthor.getBirthDate()));
        author.setNationality(createAuthor.getNationality());
        author.setBiography(createAuthor.getBiography());

        return authorRepository.save(author);
    }

    public AuthorEntity update(Long id, UpdateAuthorRequestDTO updateAuthor) {
        AuthorEntity author = findById(id);
        updateData(author, updateAuthor);

        return authorRepository.save(author);
    }

    private void updateData(AuthorEntity entity, UpdateAuthorRequestDTO updateAuthor) {
        UpdateValue.updateIfNotNull(entity::setName, updateAuthor.getName());
        UpdateValue.updateIfNotNull(entity::setBirthDate, DateHelper.dateStringToLocalDateTime(updateAuthor.getBirthDate()));
        UpdateValue.updateIfNotNull(entity::setNationality, updateAuthor.getNationality());
        UpdateValue.updateIfNotNull(entity::setBiography, updateAuthor.getBiography());
    }
}
