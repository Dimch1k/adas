package com.example.adas.controller;

import com.example.adas.model.Requirement;
import com.example.adas.service.RequirementService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/requirements")
@CrossOrigin(origins = "*")
public class RequirementController {

    private final RequirementService service;

    public RequirementController(RequirementService service) {
        this.service = service;
    }

    @GetMapping
    public List<Requirement> getAll(Authentication auth) {
        return service.getAll();
    }

    @PostMapping
    public Requirement create(@RequestBody Map<String, String> data) {
        Requirement r = new Requirement();
        r.setTitle(data.get("title"));
        r.setDescription(data.get("description"));
        r.setType(data.get("type"));
        String email = data.get("email");

        return service.create(r, email);
    }

    @PutMapping("/{id}")
    public Requirement update(@PathVariable Long id, @RequestBody Requirement r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
