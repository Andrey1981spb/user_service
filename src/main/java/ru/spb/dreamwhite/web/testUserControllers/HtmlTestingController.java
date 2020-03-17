package ru.spb.dreamwhite.web.testUserControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping ( "/" )
public class HtmlTestingController {

    @GetMapping
    public String showPage() {
        return "redirect:access_denied";
    }

}


