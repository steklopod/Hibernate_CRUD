import connection.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import service.AddressService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class Domain {

    public static void main(String[] args) throws SQLException {
        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();

        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Umanskaya 59");
        address.setPostCode("220089");

        Project project = new Project();
        project.setTitle("Hibernate CRUD");

        Employee employee = new Employee();
        employee.setFirstName("Dzmitry ");
        employee.setLastName("Kaltovich");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.AUGUST, 1);

        employee.setBirthday(new Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        Set<Employee> employees = new HashSet<Employee>();
        employees.add(employee);
        project.setEmployees(employees);

        Set<Project> projects = new HashSet<Project>();
        projects.add(project);
        employee.setProjects(projects);

        addressService.add(address);
        employeeService.add(employee);
        projectService.add(project);

        HibernateUtil.shutdown();
    }

}