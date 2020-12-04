package com.cybertek.controller;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.enums.Status;
import com.cybertek.service.ProjectService;
import com.cybertek.service.TaskService;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class  ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;


    @GetMapping("/create")
    public String createProject(Model model){
        model.addAttribute("project",new ProjectDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findManagers());


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


    @GetMapping("/complete/{project}")
    public String completeProject(@PathVariable("project")String projectCode){
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode")String projectCode,Model model){

        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("managers",userService.findManagers());


        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project")ProjectDTO project){

        projectService.update(project);


        return "redirect:/project/create";
    }

    @GetMapping("/manager/complete")
    public String getProjectByManager(Model model){

        UserDTO manager = userService.findById("john@cybertek.com");

        List<ProjectDTO> projects = getCountedListOfProjectDTO(manager);

        model.addAttribute("projects",projects);


        return "/manager/project-status";
    }


    List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager){

        List<ProjectDTO> list = projectService
                .findAll()
                .stream()
                .filter(x -> x.getAssignedManager().equals(manager))
                .map(x -> {

                    List<TaskDTO> taskList = taskService.findTaskByManager(manager);
                    int completeCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) && t.getTaskStatus() == Status.COMPLETE).count();
                    int inCompleteCount = (int) taskList.stream().filter(t -> t.getProject().equals(x) && t.getTaskStatus() != Status.COMPLETE).count();

                    x.setCompleteTaskCounts(completeCount);
                    x.setUnfinishedTaskCounts(inCompleteCount);

                    return x;

                }).collect(Collectors.toList());

        return list;

    }

    

}
