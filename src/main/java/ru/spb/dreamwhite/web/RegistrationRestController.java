package ru.spb.dreamwhite.web;

import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.repository.user.AnketUserRepository;
import ru.spb.dreamwhite.service.TokenService;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(value = RegistrationRestController.CONFIRM_REGISTRATION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRestController {

    public static final String CONFIRM_REGISTRATION_URL = "/confirmRegistration";
    public static final String REDIRECT_APPROVED_URL = "http://localhost:63342/test/src/main/webapp/access_approved.html?_ijt=4pq8jp23ivsed7lcrltrjo3g1h";
    public static final String REDIRECT_DENIED_URL = "http://localhost:63342/test/src/main/webapp/access_denied.html?_ijt=4pq8jp23ivsed7lcrltrjo3g1h";

    @Autowired
    AnketUserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @GetMapping
    public RedirectView confirmRegistration(@RequestParam("token") String token) {
        RedirectView redirectView;
        Token verificationToken = tokenService.getToken(token);
        if (verificationToken.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            redirectView = new RedirectView(REDIRECT_DENIED_URL);
        } else redirectView = new RedirectView(REDIRECT_APPROVED_URL);

        return redirectView;
    }

    @GetMapping ("/repeatedConfirm")
    public RedirectView repeateConfirmRegistration(@RequestParam Map<String, String> parameters) throws NumberParseException {
        RedirectView redirectView;
        if (userRepository.getByParameterOrAll(parameters) == null) {
            redirectView = new RedirectView(REDIRECT_DENIED_URL);
        } else redirectView = new RedirectView( REDIRECT_APPROVED_URL);

        return redirectView;
    }

}
