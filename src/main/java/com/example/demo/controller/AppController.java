package com.example.demo.controller;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_user");
        User user = userService.get(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/user/{id}")
    public ModelAndView showThisProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("user");
        User user = userService.get(id);
        mav.addObject("user", user);
        return mav;
    }

}

