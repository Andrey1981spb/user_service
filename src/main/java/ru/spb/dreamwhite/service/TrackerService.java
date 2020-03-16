package ru.spb.dreamwhite.service;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.spb.dreamwhite.model.Tracker;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.trackers.TrackerRepository;
import ru.spb.dreamwhite.repository.user.AnketUserRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFoundWithId;
import static ru.spb.dreamwhite.util.ValidationUtil.checkNotFound;

@Service("trackerService")
public class TrackerService {

    private TrackerRepository trackerRepository;
    private AnketUserRepository userRepository;

    @Autowired
    public TrackerService(@Qualifier("trackerRepository") TrackerRepository trackerRepository, AnketUserRepository userRepository) {
        this.trackerRepository = trackerRepository;
        this.userRepository = userRepository;
    }

    public Tracker create(Tracker tracker) throws NumberParseException {
        Assert.notNull(tracker, "tracker must not be null");
        String trackerPhone = tracker.getPhone();
        String trackerEmail = tracker.getEmail();
        Map<String, String> paramsUserMap = new LinkedHashMap<>();
        paramsUserMap.put("phone", trackerPhone);
        paramsUserMap.put("email", trackerEmail);
        User user = userRepository.getUserByParameterOrAll(paramsUserMap).get(0);
        if (user != null) {
            tracker.setUser_id(user.getId());
            tracker.setUser(user);
            trackerRepository.save(tracker);
        } else {
            checkNotFound(user, " with phone " + trackerPhone + " and " + trackerEmail + " not found");
        }
        return tracker;
    }

    public void delete(int id) {
        checkNotFoundWithId(trackerRepository.delete(id), id);
    }

    public Tracker get(int id) {
        return checkNotFoundWithId(trackerRepository.get(id), id);
    }

    public void update(Tracker tracker) {
        Assert.notNull(tracker, "user must not be null");
        trackerRepository.save(tracker);
    }

    public List<Tracker> getTrackerParameterOrAll(Map<String, String> paramsMap) {
        List<Tracker> trackers = trackerRepository.getTrackerByParameterOrAll(paramsMap);
        return checkNotFound(trackers, "not found");
    }

}


