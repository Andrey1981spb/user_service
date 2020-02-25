package ru.spb.dreamwhite.util.emailUtil;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PostUserListener implements ApplicationListener<OnPostUserDataEvent> {

    private static Logger logger = Logger.getLogger(PostUserListener.class.getName());

    @Override
    public void onApplicationEvent(OnPostUserDataEvent onPostUserDataEvent) {
        this.confirmPost(onPostUserDataEvent);
    }

    private void confirmPost(OnPostUserDataEvent onPostUserDataEvent) {
        logger.info("user with created");
    }
}
