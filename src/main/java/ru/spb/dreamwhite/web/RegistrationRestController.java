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
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.user.AnketUserRepository;
import ru.spb.dreamwhite.service.TokenService;
import ru.spb.dreamwhite.util.emailUtil.MailSend;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(value = RegistrationRestController.CONFIRM_REGISTRATION_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRestController {

    public static final String CONFIRM_REGISTRATION_URL = "/confirmRegistration";
    public static final String REDIRECT_APPROVED_URL = "https://dreamwhite.ru/";
    public static final String REDIRECT_DENIED_URL = "https://stackoverflow.com/";

    @Autowired
    AnketUserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    MailSend mailSend;

    @GetMapping
    public RedirectView confirmRegistration(@RequestParam("token") String token) {
        RedirectView redirectView;
        Token verificationToken = tokenService.getToken(token);
        if (verificationToken == null || verificationToken.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            tokenService.delete(token);
            redirectView = new RedirectView(REDIRECT_DENIED_URL);
        } else {
            tokenService.delete(token);
            redirectView = new RedirectView(REDIRECT_APPROVED_URL);
        }
        return redirectView;
    }

    @GetMapping("/repeatedConfirm")
    public RedirectView repeateConfirmRegistration(@RequestParam Map<String, String> parameters) throws NumberParseException {
        RedirectView redirectView;
        if (userRepository.getUserByParameterOrAll(parameters) == null) {
            redirectView = new RedirectView(REDIRECT_DENIED_URL);
        } else {
            User user = userRepository.getUserByParameterOrAll(parameters).get(0);
            mailSend.sendMail(user, tokenService.createToken(user));
            redirectView = new RedirectView(REDIRECT_APPROVED_URL);
        }
        return redirectView;
    }

}
