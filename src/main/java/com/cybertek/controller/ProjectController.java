package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class  ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;


    @GetMapping("/create")
    public String createProject(Model model){
        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findAll());


        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project")ProjectDTO project){
        projectService.save(project);
        project.setStatus(Status.OPEN);


        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String insertProject(@PathVariable("projectCode")String projectCode){
        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }

}
