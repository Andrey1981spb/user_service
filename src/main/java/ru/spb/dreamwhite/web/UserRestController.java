package ru.spb.dreamwhite.web;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.spb.dreamwhite.model.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController ("userRestController")
@RequestMapping(value = UserRestController.USER_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractUserController {

    private static Logger logger = Logger.getLogger(UserRestController.class.getName());
    public static final String USER_URL = "/customers";

    public UserRestController() {
    }

    @GetMapping("/{id}")
    public User getByIdWithoutTrackers(@PathVariable int id) {
        return super.getByIdWithoutTrackers(id);
    }

    @GetMapping("/{id}/withTrackers")
    public User getByIdWithTrackers(@PathVariable int id) {
        return super.getByIdWithTrackers(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws NumberParseException, MethodArgumentNotValidException {
        User createdUser = super.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(USER_URL + "/{id")
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
    public void update(@Valid @RequestBody User user, @PathVariable int id) throws NumberParseException, MethodArgumentNotValidException {
        super.update(user, id);
    }

    @GetMapping
    public List<User> getUserByParameterOrAll(@RequestParam Map<String, String> parameters) throws UnsupportedEncodingException, NumberParseException {
        List<User> userList;
        if (parameters.get("phone") == null) {
            userList = super.getUserByParameterOrAll(parameters);
        } else {
            String encodedValue = URLEncoder.encode((parameters.get("phone")), StandardCharsets.UTF_8.toString());
            parameters.put("phone", encodedValue);
            userList = super.getUserByParameterOrAll(parameters);
        }
        return userList;
    }

}


