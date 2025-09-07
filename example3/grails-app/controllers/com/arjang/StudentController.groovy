package com.arjang

import grails.converters.JSON
import grails.converters.XML
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StudentController {

    StudentService studentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond studentService.list(params), model: [studentCount: studentService.count()]
    }

    def show(Long id) {
        respond studentService.get(id)
    }

    /**
     * curl http://localhost:8083/example3/student/showAsJson/2
     * @param id
     * @return
     */
    def showAsJson(Long id) {
        def student = studentService.get(id)
        render student as JSON
    }

    /**
     * curl http://localhost:8083/example3/student/showAsXml/2
     * @param id
     * @return
     */
    def showAsXml(Long id) {
        def student = studentService.get(id)
        render student as XML
    }

    /**
     * curl http://localhost:8083/example3/student/showAsJson2
     * https://docs.grails.org/latest/ref/Controllers/render.html
     * @return
     */
    def showAsJson2() {
        def teachersList = Teacher.findAll()
        render(contentType: "application/json") {
            Teachers {
                for (t in teachersList) {
                    Teacher(name: t.name, lastname: t.lastname)
                }
            }
        }
    }

    /**
     * curl http://localhost:8083/example3/student/showAsXml2
     * @return
     */
    def showAsXml2() {
        def teachers = Teacher.findAll()
        render(contentType: "text/xml") {
            teachersList {
                for (t in teachers) {
                    Teacher(name: t.name, lastname: t.lastname)
                }
            }
        }
    }

    /**
     *  curl -i -X POST -H "Accept:application/json" -H "Content-Type: application/json" http://localhost:8083/example3/student/addStudent -d '{name:"studentA", studentId:"234"}'
     */
    def addStudent() {
        log.info "==JSON=" + request.JSON.name
        def student = new Student(name:request.JSON.name)
        studentService.save(student)
        redirect(uri: "/")
    }

    def create() {
        respond new Student(params)
    }

    def save(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentService.get(id)
    }

    def update(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
