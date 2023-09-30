package com.bookstore.auth;

import com.bookstore.user.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String nickname;
    private String email;
    private String password;
    private Role role;
}
