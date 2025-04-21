package com.example.adas.controller;

import com.example.adas.model.Testing;
import com.example.adas.service.TestingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/testings")
@CrossOrigin(origins = "*")
public class TestingController {

    private final TestingService service;

    public TestingController(TestingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Testing> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Testing create(@RequestBody Map<String, String> data) {
        Testing t = new Testing();
        t.setTitle(data.get("title"));
        t.setDescription(data.get("description"));
        t.setResponsible(data.get("responsible"));
        t.setPriority(Integer.parseInt(data.get("priority")));
        t.setStatus(data.get("status"));
        t.setDeadline(LocalDateTime.parse(data.get("deadline")));
        Long developmentId = Long.parseLong(data.get("developmentId"));

        return service.create(t, developmentId);
    }

    @PutMapping("/{id}")
    public Testing update(@PathVariable Long id, @RequestBody Testing t) {
        return service.update(id, t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
