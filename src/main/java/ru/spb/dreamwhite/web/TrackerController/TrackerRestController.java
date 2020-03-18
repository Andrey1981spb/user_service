package ru.spb.dreamwhite.web.TrackerController;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.spb.dreamwhite.model.Tracker;
import ru.spb.dreamwhite.service.TrackerService;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static ru.spb.dreamwhite.web.UserRestController.USER_URL;

@RestController ( "trakerRestController" )
@RequestMapping ( value = TrackerRestController.TRACKER_URL, produces = MediaType.APPLICATION_JSON_VALUE )
public class TrackerRestController {

    private static Logger logger = Logger.getLogger(TrackerRestController.class.getName());
    public static final String TRACKER_URL = USER_URL + "/trackers";

    @Autowired
    TrackerService trackerService;

    public TrackerRestController() {
    }

    @PostMapping ( consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Tracker> createTracker(@RequestBody Tracker tracker) throws MethodArgumentNotValidException, NumberParseException {
        Tracker createdTracker = trackerService.create(tracker);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(TRACKER_URL + "/{id")
                .buildAndExpand(createdTracker.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(createdTracker);
    }

    @DeleteMapping ( "/{id}" )
    @ResponseStatus ( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable int id) {
        trackerService.delete(id);
    }


    @PutMapping ( value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseStatus ( value = HttpStatus.NO_CONTENT )
    public void update(@Valid @RequestBody Tracker tracker, @PathVariable int id) {
        tracker.setId(id);
        trackerService.update(tracker);
    }

    @GetMapping
    public List<Tracker> getTrackerByParameterOrAll(@RequestParam Map<String, String> parameters) throws UnsupportedEncodingException {
        List<Tracker> trackers;
        if (parameters.get("phone") == null) {
            trackers = trackerService.getTrackerParameterOrAll(parameters);
        } else {
            String encodedValue = URLEncoder.encode((parameters.get("phone")), StandardCharsets.UTF_8.toString());
            parameters.put("phone", encodedValue);
            trackers = trackerService.getTrackerParameterOrAll(parameters);
        }
        return trackers;
    }

}


