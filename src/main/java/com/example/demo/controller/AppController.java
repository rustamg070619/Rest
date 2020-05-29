package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/all")
    public List<User> viewHomePage(Model model) {
        List<User> listUsers = userService.listAll();
        return listUsers;
    }

    @GetMapping("/admin/get/{id}")
    public User getUser(@PathVariable(name = "id") Long id) {
        return userService.get(id);
    }


    @PostMapping(value = "/admin/save")
    public void saveUser(@RequestBody User user, String role) {
        String r = role;
        userService.save(user);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

    @GetMapping("/user/{login}")
    public ModelAndView showThisProductPage(@PathVariable(name = "login") String login) {
        ModelAndView mav = new ModelAndView("user");
        User user = (User) userService.loadUserByUsername(login);
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/admin/getAuth")
    public User getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

}

