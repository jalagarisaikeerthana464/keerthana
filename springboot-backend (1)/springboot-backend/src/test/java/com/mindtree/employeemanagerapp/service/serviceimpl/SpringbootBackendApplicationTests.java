package com.mindtree.employeemanagerapp.service.serviceimpl;


import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.Mockito;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.employeemanagerapp.controller.EmployeeController;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.service.EmployeeService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class SpringbootBackendApplicationTests {
	
	@MockBean
    EmployeeService employeeService;
	
	@Autowired
    MockMvc mockMvc;
	
	private ObjectMapper om = new ObjectMapper();
	
	@Autowired(required = true)
	WebApplicationContext webApplicationContext;
	
	
	
//spring boot api get all employee test
@Test
public void findAllEmployeeAPI() throws Exception {
	mockMvc.perform(get("/api/v1/employees"))
    .andExpect(status().is(202));
}

//junit test case to find all employee in the database 
@Test
public void findAllEmployee() throws Exception {
	Employee employee = new Employee("Sai",	"Keerthana","keerthana@gmail.com");
	Employee employee1 = new Employee("manu", "vaishnavi", "vaish@gmail.com");
	Employee employee2 = new Employee("sun", "samvidha", "samvidha@gmail.com");
	List<Employee> employees = Arrays.asList(employee,employee1,employee2);
	Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
	assertEquals(3, employeeService.getAllEmployees().size());
}

@Test
public void findEmployeeByIdAPI() throws Exception {
	int id=1;
	mockMvc.perform(get("/api/v1/employees/"+id))
    .andExpect(status().is(202));
}

//junit test case to find all employee in the database 
@Test
public void findEmployeeById() throws Exception {
	Employee employee = new Employee("Sai", "Keerthana", "Keerthana@gmail.com");
	Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(employee);
	assertEquals("Keerthana",employee.getFirstName());
}



@Test
public void addEmployeeTestAPI() throws Exception {
	Employee employee = new Employee("manamma tayee", "one", "one123");
	String request = om.writeValueAsString(employee);
	MvcResult result = mockMvc
			.perform(post("/api/v1/employees",employee)).andExpect(status().is(400)).andReturn();
}


@Test
public void addEmployeeTest() throws Exception {
	Employee employee = new Employee("manu", "vaishnavi", "vaish@gmail.com");
	Mockito.when(employeeService.createEmployee(employee)).thenReturn(employee);
	assertEquals(employeeService.createEmployee(employee).getFirstName(),"manu");
}

//https://howtodoinjava.com/spring-boot2/testing/spring-boot-2-junit-5/


}
