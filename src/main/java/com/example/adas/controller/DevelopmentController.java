package com.example.adas.controller;

import com.example.adas.model.Development;
import com.example.adas.service.DevelopmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/developments")
@CrossOrigin(origins = "*")
public class DevelopmentController {

    private final DevelopmentService service;

    public DevelopmentController(DevelopmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Development> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Development create(@RequestBody Map<String, String> data) {
        Development dev = new Development();
        dev.setTitle(data.get("title"));
        dev.setDescription(data.get("description"));
        dev.setResponsible(data.get("responsible"));
        dev.setPriority(Integer.parseInt(data.get("priority")));
        dev.setStatus(data.get("status"));
        dev.setDeadline(LocalDateTime.parse(data.get("deadline")));
        Long requirementId = Long.parseLong(data.get("requirementId"));

        return service.create(dev, requirementId);
    }

    @PutMapping("/{id}")
    public Development update(@PathVariable Long id, @RequestBody Development dev) {
        return service.update(id, dev);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
