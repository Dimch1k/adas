package com.example.adas.service;

import com.example.adas.model.Customer;
import com.example.adas.model.Support;
import com.example.adas.repository.CustomerRepository;
import com.example.adas.repository.SupportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportService {

    private final SupportRepository supportRepository;
    private final CustomerRepository customerRepository;

    public SupportService(SupportRepository supportRepository, CustomerRepository customerRepository) {
        this.supportRepository = supportRepository;
        this.customerRepository = customerRepository;
    }

    public List<Support> getAll() {
        return supportRepository.findAll();
    }

    public Support create(Support support, String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        support.setCustomer(customer);
        return supportRepository.save(support);
    }

    public void delete(long id) {
        supportRepository.deleteById(id);
    }

    public Support update(Long id, Support newSupport) {
        Support support = supportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support not found"));

        support.setTitle(newSupport.getTitle());
        support.setStatus(newSupport.getStatus());
        support.setDescription(newSupport.getDescription());

        return supportRepository.save(support);
    }
}
