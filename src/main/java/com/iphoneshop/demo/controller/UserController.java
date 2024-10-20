package com.iphoneshop.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iphoneshop.demo.domain.User;
import com.iphoneshop.demo.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/admin/user")
    public String ListUserPage(Model model) {
        List<User> listUser = this.userService.handlePrintAllUser();
        model.addAttribute("users", listUser);
        return "admin/user/list-user";
    }

    @RequestMapping("/admin/user/create")
    public String CreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String CreateSuccess(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        List<User> listUser = this.userService.handleFindByEmail();
        // System.out.println(listUser);
        return "admin/user/create";
    }
}
