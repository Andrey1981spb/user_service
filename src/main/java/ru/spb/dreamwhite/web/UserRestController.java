package ru.spb.dreamwhite.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.spb.dreamwhite.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractUserController {

    private static Logger logger = Logger.getLogger(UserRestController.class.getName());

    static final String REST_URL = "/customers";

    // @GetMapping
    // public List<User> getAll() {
    //     return super.getAll();
    // }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = super.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(REST_URL + "/{id")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdUser);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        super.update(user, id);
    }

    @GetMapping
    public List<User> getByParameterOrAll(@RequestParam Map<String, String> parameters) throws UnsupportedEncodingException {
        List<User> userList;
        if (parameters.get("phone").equals(null)) {
            userList = super.getByParameterOrAll(parameters);
        } else {
            String encodedValue = URLEncoder.encode((parameters.get("phone")), StandardCharsets.UTF_8.toString());
            parameters.put("phone", encodedValue);
            userList = super.getByParameterOrAll(parameters);
        }
        return userList;
    }

}


