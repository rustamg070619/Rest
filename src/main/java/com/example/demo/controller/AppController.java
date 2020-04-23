package com.example.demo.controller;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/admin")
    public String viewHomePage(Model model) {
        List<User> listUsers = userService.listAll();
        List<Role> listRoles = userService.listAllRoles();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("rolesFromBase", listRoles);

        return "admin";
    }

    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("roles") Role roles) {
        HashSet<Role> roleFromPage = new HashSet<>();
        roleFromPage.add(roles);
        user.setRoles(roleFromPage);
        userService.save(user);

        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit")
    public String showEditUserPage(@ModelAttribute User user, @ModelAttribute("roles") Role roles) {
        HashSet<Role> roleFromPageEdit = new HashSet<>();
        roleFromPageEdit.add(roles);
        user.setRoles(roleFromPageEdit);
       userService.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping("/user/{login}")
    public ModelAndView showThisProductPage(@PathVariable(name = "login") String login) {
        ModelAndView mav = new ModelAndView("user");
        User user = (User) userService.loadUserByUsername(login);
        mav.addObject("user", user);
        return mav;
    }

}

