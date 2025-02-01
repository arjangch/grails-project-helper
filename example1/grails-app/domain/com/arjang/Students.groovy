package com.arjang

class Students {
    String name
    String lastname
    int studentId

    static constraints = {
    }
    static mapping = {
        version false
        datasource 'myotherdb'
    }
}