package com.stussy.stussyclone20220930kde.controller;


import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class AccountController {

    @GetMapping("/account/login")
    public String login(Model model,
                        @RequestParam @Nullable String email,
                        @RequestParam @Nullable String error) { //Nullable 둘중에 하나만 들어와도 된다는 뜻
        model.addAttribute("email", email ==null? "" : email);
        model.addAttribute("error", error ==null? "" : error);
        return "account/login";
    }
    @GetMapping("/account/register")
    public String register() {
        return "account/register";
    }
}
