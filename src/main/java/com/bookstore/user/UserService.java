package com.bookstore.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void register(User user) {
        userRepository.save(user);
    }

    public User login(User user) throws NoUserFoundException {
       return userRepository.getByEmail(user.getEmail()).orElseThrow(NoUserFoundException::new);
    }
}
