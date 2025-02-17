package com.arjang

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StudentsController {

    StudentsService studentsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond studentsService.list(params), model:[studentsCount: studentsService.count()]
    }

    def show(Long id) {
        respond studentsService.get(id)
    }

    def create() {
        respond new Students(params)
    }

    def save(Students students) {
        if (students == null) {
            notFound()
            return
        }

        try {
            studentsService.save(students)
        } catch (ValidationException e) {
            respond students.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'students.label', default: 'Students'), students.id])
                redirect students
            }
            '*' { respond students, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentsService.get(id)
    }

    def update(Students students) {
        if (students == null) {
            notFound()
            return
        }

        try {
            studentsService.save(students)
        } catch (ValidationException e) {
            respond students.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'students.label', default: 'Students'), students.id])
                redirect students
            }
            '*'{ respond students, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'students.label', default: 'Students'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'students.label', default: 'Students'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
