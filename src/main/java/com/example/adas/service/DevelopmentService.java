package com.example.adas.service;

import com.example.adas.model.Development;
import com.example.adas.model.Requirement;
import com.example.adas.repository.DevelopmentRepository;
import com.example.adas.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevelopmentService {

    private final DevelopmentRepository devRepo;
    private final RequirementRepository reqRepo;

    public DevelopmentService(DevelopmentRepository devRepo, RequirementRepository reqRepo) {
        this.devRepo = devRepo;
        this.reqRepo = reqRepo;
    }

    public List<Development> getAll() {
        return devRepo.findAll();
    }

    public Development create(Development dev, Long requirementId) {
        Requirement req = reqRepo.findById(requirementId)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));
        dev.setRequirement(req);
        return devRepo.save(dev);
    }

    public Development update(Long id, Development data) {
        Development dev = devRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Development not found"));

        dev.setTitle(data.getTitle());
        dev.setDescription(data.getDescription());
        dev.setResponsible(data.getResponsible());
        dev.setPriority(data.getPriority());
        dev.setDeadline(data.getDeadline());
        dev.setStatus(data.getStatus());

        return devRepo.save(dev);
    }

    public void delete(Long id) {
        devRepo.deleteById(id);
    }
}
