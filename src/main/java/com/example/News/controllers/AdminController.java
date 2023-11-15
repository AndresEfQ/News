package com.example.News.controllers;

import com.example.News.entities.News;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.News.services.NewsService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  NewsService service;

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
    model.addAttribute("toast", true);
      try {
        service.createNews(title, body);
        return "redirect:/";
      } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "create-form.html";
      }
  }

  @GetMapping("/list")
  public String list(ModelMap model) {
    List<News> newsSet = service.findAll();
    model.addAttribute("newsSet", newsSet);
    return "list-form.html";
  }

  @GetMapping("/modify/{id}")
  public String modifyGet(@PathVariable String id, ModelMap model) {
    model.addAttribute("toast", true);
    model.addAttribute("news", service.getOne(id));
    return "modify-form.html";
  }

  @PostMapping("/modify")
  public String modifyPost(@RequestParam String id, @RequestParam String title, @RequestParam String body, ModelMap model) {
    model.addAttribute("toast", true);
    try {
      service.modifyNews(id, title, body);
      return "redirect:/";
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("news", service.getOne(id));
      return "modify-form.html";
    }
  }

  @GetMapping("/delete/{id}")
  public String deleteGet(@PathVariable String id, ModelMap model) {
    model.addAttribute("news", service.getOne(id));
    return "delete-form.html";
  }

  @PostMapping("/delete")
  public String deletePost(@RequestParam String id, ModelMap model) {
    service.deleteNews(id);
    model.addAttribute("toast", true);
    return "redirect:/";
  }
}
