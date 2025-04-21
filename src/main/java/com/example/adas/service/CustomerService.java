package com.example.adas.service;

import com.example.adas.model.Customer;
import com.example.adas.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(long id) {
        customerRepository.deleteById(id);
    }


    public Customer update(Long id, Customer updated) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        customer.setName(updated.getName());
        customer.setSurname(updated.getSurname());
        customer.setPhone(updated.getPhone());
        customer.setEmail(updated.getEmail());

        return customerRepository.save(customer);
    }
}
