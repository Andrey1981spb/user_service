package ru.spb.dreamwhite.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping
    public String showPage() {
     //   return "forward:/denied/access_denied.html";
        return "redirect:/approved/access_approved.html";
    }

}
