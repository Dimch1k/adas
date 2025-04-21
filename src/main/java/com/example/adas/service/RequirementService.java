package com.example.adas.service;

import com.example.adas.model.Customer;
import com.example.adas.model.Requirement;
import com.example.adas.repository.CustomerRepository;
import com.example.adas.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepo;
    private final CustomerRepository customerRepo;

    public RequirementService(RequirementRepository requirementRepo, CustomerRepository customerRepo) {
        this.requirementRepo = requirementRepo;
        this.customerRepo = customerRepo;
    }

    public List<Requirement> getAll() {
        return requirementRepo.findAll();
    }

    public Requirement create(Requirement requirement, String customerEmail) {
        Customer customer = customerRepo.findByEmail(customerEmail);
        requirement.setCustomer(customer);
        return requirementRepo.save(requirement);
    }

    public Requirement update(Long id, Requirement newData) {
        Requirement req = requirementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Requirement not found"));

        req.setTitle(newData.getTitle());
        req.setDescription(newData.getDescription());
        req.setType(newData.getType());

        return requirementRepo.save(req);
    }

    public void delete(Long id) {
        requirementRepo.deleteById(id);
    }
}
