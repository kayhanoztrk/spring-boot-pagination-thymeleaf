package com.kayhanozturk.paginationthymeleaf.controller;

import com.kayhanozturk.paginationthymeleaf.model.User;
import com.kayhanozturk.paginationthymeleaf.service.UserService;
import com.kayhanozturk.paginationthymeleaf.util.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("hello", "Say Hello!");
        return "helloworld";
    }

    @GetMapping("/pagination")
    public String findPaginatedAll(@RequestParam(value = "currentPage") int currentPage, Model model){
        Pageable pageable = PageRequest.of(currentPage - 1, UserConstants.PAGE_SIZE);
        Page<User> userPage = userService.findAll(pageable);

        List<User> userList = userPage.getContent();
        model.addAttribute("users", userList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        return "user/users-list";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        int currentPage = 1;
        return findPaginatedAll(currentPage, model);
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/user-form";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }


}
