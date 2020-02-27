package ru.spb.dreamwhite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.service.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = RegistrationRestController.CONFIRM_REGISTRATION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRestController {

    public static final String CONFIRM_REGISTRATION_URL = "/confirmRegistration";

    @Autowired
    UserService userService;

    @GetMapping
    public RedirectView confirmRegistration(@RequestParam("token") String token) {
        RedirectView redirectView;
        Token verificationToken = userService.getToken(token);
        if ((verificationToken == null) || (verificationToken.getExpiryDateTime().isAfter(LocalDateTime.now()))) {
            redirectView = new RedirectView("access_denied", true);
              } else redirectView = new RedirectView("access_approved", true);

        return redirectView;

    }

}
