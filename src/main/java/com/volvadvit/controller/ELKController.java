package com.volvadvit.controller;

import com.volvadvit.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ELKController {

    Logger logger = LoggerFactory.getLogger(ELKController.class);

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        List<User> users = getUsers();
        User user = users
                    .stream()
                    .filter(u -> u.getId() == id)
                    .findAny()
                    .orElse(null);

        if (user != null) {
            logger.info("user found : {}",user);
            return ResponseEntity.ok(user);
        } else {
            logger.error("User Not Found with ID : {}",id);
            return ResponseEntity.badRequest().body(new User(400, "User not found"));
        }
    }

    private List<User> getUsers() {
        return Stream
                .of(
                    new User(1, "John"),
                    new User(2, "Nick"),
                    new User(3, "Rony"),
                    new User(4, "Mike"))
                .collect(Collectors.toList());
    }
}
