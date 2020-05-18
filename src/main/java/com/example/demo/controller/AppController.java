package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/admin/save")
    public void saveUser(@RequestBody User user) {
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

}

