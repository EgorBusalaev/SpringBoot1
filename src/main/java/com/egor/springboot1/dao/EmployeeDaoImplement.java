package com.egor.springboot1.dao;

import com.egor.springboot1.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Query;


import java.util.List;

@Repository // аннотация используется при поиске компронентов для DAO
public class EmployeeDaoImplement implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {

//
//        Session session = entityManager.unwrap(Session.class);//эта заппись вместо getcurrentsession
//        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();
        Query query = entityManager.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();


        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);
      Employee newEmployee =   entityManager.merge(employee);
      employee.setId(newEmployee.getId()); // получаем id что бы корректно отображалось в postman
    }

    @Override
    public Employee getEmployee(int id) {

//        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class, id);
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();
       Query query = entityManager.createQuery("delete from Employee where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

}
