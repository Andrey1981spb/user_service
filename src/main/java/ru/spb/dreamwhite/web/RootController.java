package ru.spb.dreamwhite.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spb.dreamwhite.util.emailUtil.MailSend;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class RootController {
    public static String HOSTNAME = null;
    private static final Logger LOGGER = Logger.getLogger(MailSend.class.getName());

    @GetMapping
    public String showPage(HttpServletRequest request) {
        HOSTNAME = request.getServerName();
        LOGGER.info("HOSTNAME IS " + HOSTNAME);
     //   return "forward:/denied/access_denied.html";
        return "redirect:/approved/access_approved.html";
    }

}
