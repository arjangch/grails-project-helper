package com.arjang

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

/**
 * This annotation will not work with Requestmap Instances Stored in the Database. securityConfigType = 'Requestmap'.
 * But it will work in conjunction with Static Map
 */
//@Secured(['permitAll'])
class CarsController {

    CarsService carsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     *
     */
//    @Secured(['permitAll'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond carsService.list(params), model:[carsCount: carsService.count()]
    }

//    @Secured(['permitAll'])
    def show(Long id) {
        respond carsService.get(id)
    }

    def edit(Long id) {
        respond carsService.get(id)
    }

    def create() {
        respond new Cars(params)
    }

    def save(Cars cars) {
        if (cars == null) {
            notFound()
            return
        }

        try {
            carsService.save(cars)
        } catch (ValidationException e) {
            respond cars.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cars.label', default: 'Cars'), cars.id])
                redirect cars
            }
            '*' { respond cars, [status: CREATED] }
        }
    }

    def update(Cars cars) {
        if (cars == null) {
            notFound()
            return
        }

        try {
            carsService.save(cars)
        } catch (ValidationException e) {
            respond cars.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cars.label', default: 'Cars'), cars.id])
                redirect cars
            }
            '*'{ respond cars, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        carsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cars.label', default: 'Cars'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cars.label', default: 'Cars'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
