package com.work.backendlibrary.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.work.backendlibrary.Views.VentaView;
import com.work.backendlibrary.entity.User;
import com.work.backendlibrary.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Log LOGGER = LogFactory.getLog(UserController.class);

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @JsonView(VentaView.user.class)
    @PostMapping(value = "/logincheck")
    public ResponseEntity<User> autenticar(@RequestBody User user) {
        User usuario = userRepository.findByUsername(user.getUsername());
        if (usuario != null)
            return new ResponseEntity<User>(usuario, HttpStatus.OK);
        else
            return new ResponseEntity<User>(usuario, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

