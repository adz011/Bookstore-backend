package com.bookstore.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class User {
    @Id
    private long id;
    @Id
    private String email;
    private String username;
}
