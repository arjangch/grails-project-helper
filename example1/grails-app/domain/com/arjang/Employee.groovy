package com.arjang

class Employee {
    String name
    String lastname
    int empoyeeId
    String employeeNotes

    static constraints = {
    }
    static mapping = {
        employeeNotes type: "text"
    }
}
