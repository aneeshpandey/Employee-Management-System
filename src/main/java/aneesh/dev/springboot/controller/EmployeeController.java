package aneesh.dev.springboot.controller;

import aneesh.dev.springboot.model.Employee;
import aneesh.dev.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index"; //thymeleaf template
    }

    // Show new form to add new Employee details
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // Save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id, Model model) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    // Show form to update existing Employee details
    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateEmployeeForm(@PathVariable (value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee",employee);
        return "update_employee";
    }
}
