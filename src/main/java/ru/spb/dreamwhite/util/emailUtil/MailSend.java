package ru.spb.dreamwhite.util.emailUtil;

import com.sendgrid.Method;
import com.sendgrid.Request;;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spb.dreamwhite.model.Token;
import ru.spb.dreamwhite.model.User;

import java.io.IOException;
import java.util.logging.Logger;

import static ru.spb.dreamwhite.web.RegistrationRestController.CONFIRM_REGISTRATION_URL;
import static ru.spb.dreamwhite.web.UserRestController.REST_URL;

@Component
public class MailSend {

    @Autowired
    private SendGrid sendGrid;

    private static final Logger LOGGER = Logger.getLogger(MailSend.class.getName());

    public boolean sendMail(User user, Token token) {
        String recipient = user.getEmail();
        String subject = "Registration Confirmation";
        String url = "/test" + CONFIRM_REGISTRATION_URL + "?token=" + token.getToken();
        String message = "Спасибо за регистрацию! Пожалуйста перейдите по нижеуказанной ссылке для активации Вашего аккаунта: ";

        Email from = new Email("no-reply@dreamwhite.ru");
        Email to = new Email(recipient);
        Content content = new Content("text/plain", message + "http://localhost:8080" + url);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);

        } catch (IOException ex) {
            LOGGER.info(ex.getMessage());
        }

        return true;
    }

}
