package com.fcmm.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String welcome(Model model) {
    model.addAttribute("greeting", "Welcome to Integration Challenge!");
    model.addAttribute("tagline", "(...and my first Spring project.  Hey, gotta start somewhere.)");
    
    return "welcome";
  }
}
