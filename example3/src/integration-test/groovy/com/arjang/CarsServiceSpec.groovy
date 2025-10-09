package com.arjang

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CarsServiceSpec extends Specification {

    CarsService carsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cars(...).save(flush: true, failOnError: true)
        //new Cars(...).save(flush: true, failOnError: true)
        //Cars cars = new Cars(...).save(flush: true, failOnError: true)
        //new Cars(...).save(flush: true, failOnError: true)
        //new Cars(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cars.id
    }

    void "test get"() {
        setupData()

        expect:
        carsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cars> carsList = carsService.list(max: 2, offset: 2)

        then:
        carsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        carsService.count() == 5
    }

    void "test delete"() {
        Long carsId = setupData()

        expect:
        carsService.count() == 5

        when:
        carsService.delete(carsId)
        sessionFactory.currentSession.flush()

        then:
        carsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cars cars = new Cars()
        carsService.save(cars)

        then:
        cars.id != null
    }
}
