package ru.spb.dreamwhite.web;

import com.google.i18n.phonenumbers.NumberParseException;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.model.User;
import ru.spb.dreamwhite.repository.user.AnketUserRepository;
import ru.spb.dreamwhite.service.TokenService;
import ru.spb.dreamwhite.util.emailUtil.MailSend;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping( value = TestRegistrationController.TEST_CONFIRM_REGISTRATION_URL )
public class TestRegistrationController {

    public static final String TEST_CONFIRM_REGISTRATION_URL = "/testConfirmRegistration";
    public static final String TEST_FORWARD_DENIED_ACCESS = "forward:/denied/access_denied.html";
    public static final String TEST_FORWARD_DENIED_APPROVED = "forward:/";

    @Autowired
    AnketUserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    MailSend mailSend;

    @GetMapping
    public String confirmRegistration(@RequestParam("token") String token) {
        String responce;
        Token verificationToken = tokenService.getToken(token);
        if (verificationToken == null || verificationToken.getExpiryDateTime().isBefore(LocalDateTime.now())) {
            tokenService.delete(token);
            responce = TEST_FORWARD_DENIED_ACCESS;
        }
        else {
            tokenService.delete(token);
            responce = TEST_FORWARD_DENIED_APPROVED;
        }
        return responce;
    }


    @GetMapping ("/testRepeatedConfirm")
    public String repeateConfirmRegistration(@RequestParam Map<String, String> parameters) throws NumberParseException {
        String responce;
        if (userRepository.getByParameterOrAll(parameters) == null) {
            responce = TEST_FORWARD_DENIED_ACCESS;
        }
        else {
            User user=  userRepository.getByParameterOrAll(parameters).get(0);
            mailSend.sendMail(user, tokenService.createToken(user));
            responce = TEST_FORWARD_DENIED_APPROVED;
        }
        return responce;
    }

}
