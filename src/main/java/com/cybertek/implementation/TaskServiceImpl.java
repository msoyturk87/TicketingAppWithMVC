package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService {
    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {

        /*List<TaskDTO> sortedTasks = new ArrayList<>();
        sortedTasks=super.findAll().stream().sorted(Comparator.comparing(TaskDTO::getId))
                .collect(Collectors.toList());



        Optional<TaskDTO> last = sortedTasks.stream().reduce((a, b) -> b);

        Long lastId = last.isPresent() ? last.get().getId() : 0;

        taskDTO.setId(lastId + 1);
        return super.save(taskDTO.getId(),taskDTO);*/
        return super.save(taskDTO.getId(),taskDTO);
    }

    @Override
    public void update(TaskDTO object) {

        TaskDTO foundProject=findById(object.getId());
        object.setAssignDate(foundProject.getAssignDate());
        object.setTaskStatus(foundProject.getTaskStatus());
        object.setId(object.getId());

        super.update(object.getId(),object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(TaskDTO object) {
        super.delete(object);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findTaskByManager(UserDTO manager) {
        return super.findAll()
                .stream()
                .filter(task->task.getProject()
                        .getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }
}
