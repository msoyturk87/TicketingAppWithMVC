package com.cybertek.controller;

import com.cybertek.dto.RoleDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.implementation.RoleServiceImpl;
import com.cybertek.service.RoleService;
import com.cybertek.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
@Component
public class UserController {


    RoleService roleService;
    UserService userService;


    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping({"/create","/add","/initialize"})
    public String createUser(Model model){


        model.addAttribute("user",new UserDTO());
        model.addAttribute("roleList",roleService.findAll());
        //DataGenerator need for Role so we should create service for this
        return "/user/create";
    }

    @PostMapping("/create")
    public String submitForm(@ModelAttribute("user") UserDTO userDTO,Model model){

        userService.save(userDTO);
        model.addAttribute("userList",userService.findAll());


        return "user/create";
    }

}
