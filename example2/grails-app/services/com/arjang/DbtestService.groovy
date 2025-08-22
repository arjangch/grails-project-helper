package com.arjang

import groovy.transform.CompileStatic
import grails.gorm.transactions.Transactional

@CompileStatic
class DbtestService {

    @Transactional(readOnly = true, connection = "myotherdb")
    def withAnnotation() {
        def oneemployee = new Employee(name: "Louis", lastname: "mandy", employeeId: 1111, employeeNotes: "my robot112").save(flush: true, failOnError: true)
        log.info '-1.1--=' + Employee.list()
        return "back from withAnnotation"
    }

    def usingTransaction() {
        // this works
        Employee.withTransaction {
            def oneemployee = new Employee(name: "Louis2", lastname: "mandy2", employeeId: 22222, employeeNotes: "my 2 robot112").save(flush: true, failOnError: true)
            log.info '-2.1--=' + Employee.list()
        }
        return "back from usingTransaction"
    }

    def createNewSession(){
        // Doesn't work
        Employee.withNewSession { session ->
            // cannot add Employee with new session into 2nd db.
            def oneemployee = new Employee(name: "Louis3", lastname: "mandy3", employeeId: 3333, employeeNotes: "my 3 3").save(flush: true, failOnError: true)
        }
        return "back from createNewSession"
    }

    def listWithNewSession(){
        // This work
        Employee.withNewSession { session ->
            log.info '-2.2--='+Employee.list()
        }
        return "back from listWithNewSession"
    }

    def usingSession(){
        // This work
        Employee.withSession { session ->
            log.info '-3.2--='+Employee.list()
        }
        return "back from usingSession"
    }

    def justCallObject(){
        // this work
        log.info '-4.2-=' + Employee.list()
        return "back from justCallObject"
    }

}