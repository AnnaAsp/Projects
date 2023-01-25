package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.*;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping(value = "/tasks", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity createTask(@RequestBody Task task) {
        Task newTask = taskRepository.save(task);
        newTask.setCreationTime(LocalDateTime.now());
        newTask.setDone(false);
        taskRepository.save(newTask);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public List<Task> getTaskList() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PatchMapping(value = "/tasks/{id}", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity updateTask(@RequestBody Task task, @PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task oldTask = optionalTask.get();
        if (task.getTitle() != null) {
            oldTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            oldTask.setDescription(task.getDescription());
        }
        if (task.isDone()) {
            oldTask.setDone(true);
        } else {
            oldTask.setDone(false);
        }
        taskRepository.save(oldTask);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.delete(optionalTask.get());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
