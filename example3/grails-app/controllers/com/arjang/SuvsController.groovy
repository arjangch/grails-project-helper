package com.arjang

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SuvsController {

    SuvsService suvsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond suvsService.list(params), model:[suvsCount: suvsService.count()]
    }

    def show(Long id) {
        respond suvsService.get(id)
    }

    def create() {
        respond new Suvs(params)
    }

    def save(Suvs suvs) {
        if (suvs == null) {
            notFound()
            return
        }

        try {
            suvsService.save(suvs)
        } catch (ValidationException e) {
            respond suvs.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'suvs.label', default: 'Suvs'), suvs.id])
                redirect suvs
            }
            '*' { respond suvs, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond suvsService.get(id)
    }

    def update(Suvs suvs) {
        if (suvs == null) {
            notFound()
            return
        }

        try {
            suvsService.save(suvs)
        } catch (ValidationException e) {
            respond suvs.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'suvs.label', default: 'Suvs'), suvs.id])
                redirect suvs
            }
            '*'{ respond suvs, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        suvsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'suvs.label', default: 'Suvs'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'suvs.label', default: 'Suvs'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
