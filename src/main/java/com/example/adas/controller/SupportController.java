package com.example.adas.controller;

import com.example.adas.model.Support;
import com.example.adas.service.SupportService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/support")
public class SupportController {

    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }

//    @GetMapping
//    public List<Support> getAll() {
//        return supportService.getAll();
//    }

    @GetMapping
    public List<Map<String, Object>> getAll(Authentication auth) {
        boolean isCustomer = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"));

        return supportService.getAll().stream().map(s -> {
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("id", s.getId());
            result.put("title", s.getTitle());
            result.put("description", s.getDescription());
            result.put("status", s.getStatus());

            if (!isCustomer && s.getCustomer() != null) {
                Map<String, Object> customer = new HashMap<>();
                customer.put("id", s.getCustomer().getId());
                customer.put("name", s.getCustomer().getName());
                customer.put("surname", s.getCustomer().getSurname());
                customer.put("email", s.getCustomer().getEmail());
                customer.put("phone", s.getCustomer().getPhone());
                result.put("customer", customer);
            }

            return result;
        }).collect(Collectors.toList());
    }


    @PostMapping
    public Support create(@RequestBody Map<String, String> data) {
        Support support = new Support();
        support.setTitle(data.get("title"));
        support.setDescription(data.get("description"));
        support.setStatus(data.get("status"));
        String email = data.get("email");

        return supportService.create(support, email);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        supportService.delete(id);
    }

    @PutMapping("/{id}")
    public Support update(@PathVariable Long id, @RequestBody Support support) {
        return supportService.update(id, support);
    }
}
