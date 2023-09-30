package com.vitslepukhin.zator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class RootController {
    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("url", "");
        return "index";
    }
}
