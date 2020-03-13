package ru.spb.dreamwhite.web.testControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping ( "/" )
public class HtmlTestingController {

    @GetMapping
    public String showPage() {
        return "redirect:access_denied";
    }

}


