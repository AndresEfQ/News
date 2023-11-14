package com.example.News.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @GetMapping("")
  public String admin() {
    return "admin.html";
  }

  @GetMapping("/create")
  public String createGet() {
    return "create-form.html";
  } 

  @PostMapping("/create")
  public String createPost(
    @RequestParam (required = false) String title, 
    @RequestParam (required = false) String body,
    ModelMap model
  ) {
      try {
        // create news
      } catch (Exception e) {
        
      }
  }

  @GetMapping("/list")
  public String list() {
    return "list-form.html";
  }
}
