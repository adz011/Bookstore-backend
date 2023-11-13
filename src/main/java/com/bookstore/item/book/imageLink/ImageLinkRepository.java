package com.bookstore.item.book.imageLink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageLinkRepository extends JpaRepository<ImageLinks, Long> {
}
