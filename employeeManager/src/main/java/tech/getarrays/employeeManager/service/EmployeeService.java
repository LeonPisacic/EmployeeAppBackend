package tech.getarrays.employeeManager.service;

import java.util.UUID;
import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.getarrays.employeeManager.exception.UserNotFoundException;
import tech.getarrays.employeeManager.model.Employee;
import tech.getarrays.employeeManager.repo.EmployeeRepo;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    // @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.saveAndFlush(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }
}
