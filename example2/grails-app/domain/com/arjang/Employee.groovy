package com.arjang

class Employee {
    String name
    String lastname
    int employeeId
    String employeeNotes

    static constraints = {

    }
    static mapping = {
        datasource 'myotherdb'
        employeeNotes type: "text"
    }
}
