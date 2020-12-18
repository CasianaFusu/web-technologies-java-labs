package com.unibuc.lab10.service;

import com.unibuc.lab10.domain.Employee;
import com.unibuc.lab10.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee getBy(Long id) {
        return repository.findBy(id)
                .orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public List<Employee> getAll(){
        return repository.findAll();
    }

    @Transactional
    public void update(Long id, String name) {
        repository.updatePromotion(id);
        repository.updateSalary(id, name);
    }
}
