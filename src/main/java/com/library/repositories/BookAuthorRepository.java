package com.library.repositories;

import com.library.entities.AuthorEntity;
import com.library.entities.BookAuthorEntity;
import com.library.entities.BookEntity;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, Long> {
    Optional<BookAuthorEntity> findByBookIdAndAuthorId(Long bookId, Long authorId);

    @Query("""
        SELECT ba
        FROM BookAuthorEntity ba
        WHERE ba.author.id = :authorId
    """)
    List<BookAuthorEntity> findAllLinksByAuthorId(@Param("authorId") Long authorId);

    @Query("""
        SELECT ba
        FROM BookAuthorEntity ba
        WHERE ba.book.id = :bookId
    """)
    List<BookAuthorEntity> findAllLinksByBookId(@Param("bookId") Long bookId);

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM BookAuthorEntity ba
        WHERE ba.book.id = :bookId
          AND ba.author.id = :authorId
    """)
    void deleteByBookIdAndAuthorId(@Param("bookId") Long bookId, @Param("authorId") Long authorId);
}
