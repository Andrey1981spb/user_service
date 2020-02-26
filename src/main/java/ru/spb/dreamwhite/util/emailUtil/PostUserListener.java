package ru.spb.dreamwhite.util.emailUtil;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import ru.spb.dreamwhite.model.User;

import java.io.IOException;
import java.util.logging.Logger;

@Component
public class PostUserListener implements ApplicationListener<OnPostUserDataEvent> {

    private static Logger logger = Logger.getLogger(PostUserListener.class.getName());

    @Autowired
    SimpleMailMessage email;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SendGrid sendGrid;

    @Override
    public void onApplicationEvent(OnPostUserDataEvent onPostUserDataEvent) {
        this.confirmPost(onPostUserDataEvent);
    }

    private void confirmPost(OnPostUserDataEvent onPostUserDataEvent) {
        logger.info("new user " + onPostUserDataEvent.getUser().getName() + " is created");
        User user = onPostUserDataEvent.getUser();
        String recipient = user.getEmail();
        String subject = "Registration Confirmation";
        String url = onPostUserDataEvent.getAppUrl();
        String message = "Спасибо за регистрацию! Пожалуйста перейдите по нижеуказанной ссылке для активации Вашего аккаунта";

/*
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(message + "http://localhost:8080" + url);
        mailSender.send(email);

 */

        Email from = new Email("no-reply@dreamwhite.ru");
        Email to = new Email(recipient);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            sendGrid.api(request);

        } catch (IOException ex) {
            logger.info(ex.getMessage());
        }


    }
}
