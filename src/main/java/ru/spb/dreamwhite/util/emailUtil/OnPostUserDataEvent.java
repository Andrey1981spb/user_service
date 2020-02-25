package ru.spb.dreamwhite.util.emailUtil;

import org.springframework.context.ApplicationEvent;
import ru.spb.dreamwhite.model.User;

public class OnPostUserDataEvent extends ApplicationEvent {

    // https://medium.com/@angela.amarapala/sending-email-confirmation-for-account-activation-with-spring-java-cc3f5bb1398e
    private static final long serialVersionUID = 1L;
    private String appUrl;

    private User user;

    public OnPostUserDataEvent(User user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
