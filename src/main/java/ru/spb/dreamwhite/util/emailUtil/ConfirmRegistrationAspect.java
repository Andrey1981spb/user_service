package ru.spb.dreamwhite.util.emailUtil;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.logging.Logger;

import static ru.spb.dreamwhite.util.emailUtil.MailSend.CONFIRM_REGISTRATION_ENDPOINT;
import static ru.spb.dreamwhite.web.RegistrationRestController.CONFIRM_REGISTRATION_URL;
import static ru.spb.dreamwhite.web.testUserControllers.TestRegistrationController.TEST_CONFIRM_REGISTRATION_URL;
import static ru.spb.dreamwhite.web.RootController.HOSTNAME;

@Aspect
public class ConfirmRegistrationAspect {

    private static final Logger LOGGER = Logger.getLogger(ConfirmRegistrationAspect.class.getName());

    @Pointcut("execution(* ru.spb.dreamwhite.web.RootController*.*(..))")
    public void onMailSend() {
    }

    @After("onMailSend ()")
    public void beforeSendEmailExecution() {
        if (HOSTNAME.equals("localhost")) {
            CONFIRM_REGISTRATION_ENDPOINT = ":8080" + TEST_CONFIRM_REGISTRATION_URL;
            LOGGER.info("CONFIRM_REGISTRATION_ENDPOINT IS " + CONFIRM_REGISTRATION_ENDPOINT);
        } else {
            CONFIRM_REGISTRATION_ENDPOINT = CONFIRM_REGISTRATION_URL;
        }
    }

}
