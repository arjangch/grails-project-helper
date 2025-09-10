package com.arjang

class Student {
    String name
    String lastname
    String grade
    int studentId

    static constraints = {
        name nullable: false
        lastname nullable: true
        grade nullable: true
        studentId nullable: false
    }
}
