package com.noaats.jspark.user.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<Template> getUser(@PathVariable String userId) {
        return ResponseEntity.ok(new Template("name", 15));
    }

    public record Template(String name, int age) {

    }
}
