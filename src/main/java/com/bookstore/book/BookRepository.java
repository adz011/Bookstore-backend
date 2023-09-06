package com.bookstore.book;

import com.bookstore.book.base.BookDetails;
import com.bookstore.book.base.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookInfo, Long> {
}
