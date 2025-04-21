package com.example.adas.service;

import com.example.adas.model.Development;
import com.example.adas.model.Testing;
import com.example.adas.repository.DevelopmentRepository;
import com.example.adas.repository.TestingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestingService {

    private final TestingRepository testingRepo;
    private final DevelopmentRepository developmentRepo;

    public TestingService(TestingRepository testingRepo, DevelopmentRepository developmentRepo) {
        this.testingRepo = testingRepo;
        this.developmentRepo = developmentRepo;
    }

    public List<Testing> getAll() {
        return testingRepo.findAll();
    }

    public Testing create(Testing t, Long developmentId) {
        Development dev = developmentRepo.findById(developmentId)
                .orElseThrow(() -> new RuntimeException("Development not found"));
        t.setDevelopment(dev);
        return testingRepo.save(t);
    }

    public Testing update(Long id, Testing data) {
        Testing t = testingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Testing not found"));

        t.setTitle(data.getTitle());
        t.setDescription(data.getDescription());
        t.setResponsible(data.getResponsible());
        t.setPriority(data.getPriority());
        t.setDeadline(data.getDeadline());
        t.setStatus(data.getStatus());

        return testingRepo.save(t);
    }

    public void delete(Long id) {
        testingRepo.deleteById(id);
    }
}
