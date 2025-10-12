package com.arjang

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BikesController {

    BikesService bikesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond bikesService.list(params), model:[bikesCount: bikesService.count()]
    }

    def show(Long id) {
        respond bikesService.get(id)
    }

    def create() {
        respond new Bikes(params)
    }

    def save(Bikes bikes) {
        if (bikes == null) {
            notFound()
            return
        }

        try {
            bikesService.save(bikes)
        } catch (ValidationException e) {
            respond bikes.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bikes.label', default: 'Bikes'), bikes.id])
                redirect bikes
            }
            '*' { respond bikes, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond bikesService.get(id)
    }

    def update(Bikes bikes) {
        if (bikes == null) {
            notFound()
            return
        }

        try {
            bikesService.save(bikes)
        } catch (ValidationException e) {
            respond bikes.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bikes.label', default: 'Bikes'), bikes.id])
                redirect bikes
            }
            '*'{ respond bikes, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        bikesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bikes.label', default: 'Bikes'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bikes.label', default: 'Bikes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
