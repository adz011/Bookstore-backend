package com.bookstore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws NoUserFoundException {
        userService.register(user);
        return ResponseEntity.ok().body(userService.login(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws NoUserFoundException {
        return ResponseEntity.ok().body(userService.login(user));
    }

}
