package ru.spb.dreamwhite.util.emailUtil;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static ru.spb.dreamwhite.util.emailUtil.MailSend.CONFIRM_REGISTRATION_ENDPOINT;
import static ru.spb.dreamwhite.web.RegistrationRestController.CONFIRM_REGISTRATION_URL;
import static ru.spb.dreamwhite.web.TestRegistrationController.TEST_CONFIRM_REGISTRATION_URL;
import static ru.spb.dreamwhite.web.UserRestController.HOSTNAME;

@Aspect
public class ConfirmRegistrationAspect {

    @Before(value = "ru.spb.dreamwhite.util.emailUtil.ConfirmRegistrationPointcut.registrationUrlLayer()")
    public void beforeSendEmailExecution() {
        if (HOSTNAME.equals("localhost")) {
            CONFIRM_REGISTRATION_ENDPOINT = TEST_CONFIRM_REGISTRATION_URL;
        } else {
            CONFIRM_REGISTRATION_ENDPOINT = CONFIRM_REGISTRATION_URL;
        }
    }

}
