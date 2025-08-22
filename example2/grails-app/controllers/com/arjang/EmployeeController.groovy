package com.arjang

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true, connection = "myotherdb")
class EmployeeController {

    EmployeeService employeeService
    DbtestService dbtestService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def withAnnotation(){
        log.info '-1--='+ dbtestService.withAnnotation()
        redirect(uri: "/")
    }

    def usingTransaction() {
        log.info '-2--='+ dbtestService.usingTransaction()
        redirect(uri: "/")
    }

    def createNewSession() {
        log.info '-2--='+ dbtestService.createNewSession()
        redirect(uri: "/")
    }

    def listWithNewSession() {
        log.info '-2--='+ dbtestService.listWithNewSession()
        redirect(uri: "/")
    }

    def usingSession() {
        log.info '-2--='+ dbtestService.usingSession()
        redirect(uri: "/")
    }

    def justCallObject() {
        log.info '-2--='+ dbtestService.justCallObject()
        redirect(uri: "/")
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond employeeService.list(params), model:[employeeCount: employeeService.count()]
    }

    def show(Long id) {
        respond employeeService.get(id)
    }

    def create() {
        respond new Employee(params)
    }

    def save(Employee employee) {
        if (employee == null) {
            notFound()
            return
        }

        try {
            employeeService.save(employee)
        } catch (ValidationException e) {
            respond employee.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*' { respond employee, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond employeeService.get(id)
    }

    def update(Employee employee) {
        if (employee == null) {
            notFound()
            return
        }

        try {
            employeeService.save(employee)
        } catch (ValidationException e) {
            respond employee.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'employee.label', default: 'Employee'), employee.id])
                redirect employee
            }
            '*'{ respond employee, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        employeeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'employee.label', default: 'Employee'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'employee.label', default: 'Employee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
