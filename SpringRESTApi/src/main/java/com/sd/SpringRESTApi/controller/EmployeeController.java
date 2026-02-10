package com.sd.SpringRESTApi.controller;

import com.sd.SpringRESTApi.entity.Employee;
import com.sd.SpringRESTApi.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final IEmployeeService employeeService;
    /*
    * JsonMapper is a helper class in Jackson library for Json processing.
    * It converts JAVA object to Json and vice-versa.
    * It allows merging of Json nodes.
    * */
    private final JsonMapper jsonMapper;

    @Autowired
    public EmployeeController(IEmployeeService employeeService, JsonMapper jsonMapper){
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getAll(){
        return this.employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getById(@PathVariable int employeeId){
        Employee result = this.employeeService.findById(employeeId);
        if(result==null){
            throw new RuntimeException("Employee not found with id: "+employeeId);
        }
        return result;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee newEmployee){
        //By setting employeeId=0, we're forcing to insert instead of update.
        newEmployee.setId(0);
        return this.employeeService.save(newEmployee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        return this.employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = this.employeeService.findById(employeeId);

        if(tempEmployee==null){
            throw new RuntimeException("Employee id not found- "+employeeId);
        }

       this.employeeService.deleteById(employeeId);
       return "Employee with id: "+employeeId+" deleted successfully";
    }

    /*
    * HTTP PATCH- Used for partial update, whereas as PUT is used for full update.
    * Here RequestBody will have some fields of Employee to update not all fields.
    * Example Payload- {"email": "abc@mai.com", "firstName": "Anil"}
    * */
    @PatchMapping("/employees/{employeeId}")
    public Employee patchUpdate(@PathVariable int employeeId, @RequestBody Map<String, Object> payload){
        //check Employee exists or not
        Employee targetEmployee = this.employeeService.findById(employeeId);

        if(targetEmployee==null){
            throw new RuntimeException("Employee id not found- "+employeeId);
        }

        //don't allow to update id field
        if(payload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in payload- "+employeeId);
        }

        //apply partial update to existing employee object by key matching.
        Employee patchedEmployee = this.jsonMapper.updateValue(targetEmployee, payload);

        Employee result = this.employeeService.save(patchedEmployee);
        return result;
    }
}
