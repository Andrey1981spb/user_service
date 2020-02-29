package ru.spb.dreamwhite.util.emailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.spb.dreamwhite.model.User;

import static ru.spb.dreamwhite.web.UserRestController.REST_URL;

@Component
public class CustomEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(User user) {
        OnPostUserDataEvent onPostUserDataEvent = new OnPostUserDataEvent(user, REST_URL);
        applicationEventPublisher.publishEvent(onPostUserDataEvent);
    }

}
