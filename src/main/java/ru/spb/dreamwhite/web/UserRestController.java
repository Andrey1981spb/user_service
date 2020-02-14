package ru.spb.dreamwhite.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.spb.dreamwhite.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractUserController {

    static final String REST_URL = "/users";

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser (@Valid @RequestBody User user){
        User createdUser = super.create(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path(REST_URL + "/{id")
                    .buildAndExpand(createdUser.getId())
                    .toUri();
            return ResponseEntity.created(uri)
                    .body(createdUser);
    }



}
