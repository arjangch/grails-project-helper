package com.nelson

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CliTestController {

    CliTestService cliTestService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def command = ["op", "read", "op://Employee/Testing-Login/username"]
        def process = command.execute()
        def output = new StringBuffer()
        def error = new StringBuffer()
        process.waitForProcessOutput(output, error)
        def username = output.toString()
        println "username=" + username
        if (error)
            println "U-error=" + error

        command = ["op", "read", "op://Employee/Testing-Login/password"]
        process = command.execute()
        def poutput = new StringBuffer()
        def perror = new StringBuffer()
        process.waitForProcessOutput(poutput, perror)
        def password = poutput.toString()
        println "password=" + password
        if (perror)
            println "P-error=" + perror

        respond cliTestService.list(params), model: [cliTestCount: cliTestService.count()]
    }

    def show(Long id) {
        respond cliTestService.get(id)
    }

    def create() {
        respond new CliTest(params)
    }

    def save(CliTest cliTest) {
        if (cliTest == null) {
            notFound()
            return
        }

        try {
            cliTestService.save(cliTest)
        } catch (ValidationException e) {
            respond cliTest.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cliTest.label', default: 'CliTest'), cliTest.id])
                redirect cliTest
            }
            '*' { respond cliTest, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond cliTestService.get(id)
    }

    def update(CliTest cliTest) {
        if (cliTest == null) {
            notFound()
            return
        }

        try {
            cliTestService.save(cliTest)
        } catch (ValidationException e) {
            respond cliTest.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cliTest.label', default: 'CliTest'), cliTest.id])
                redirect cliTest
            }
            '*' { respond cliTest, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        cliTestService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cliTest.label', default: 'CliTest'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliTest.label', default: 'CliTest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
