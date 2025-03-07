package com.arjang

import grails.gorm.services.Service
import com.arjang.Employee

@Service(Employee)
interface EmployeeService {

    Employee get(Serializable id)

    List<Employee> list(Map args)

    Long count()

    void delete(Serializable id)

    Employee save(Employee employee)

}