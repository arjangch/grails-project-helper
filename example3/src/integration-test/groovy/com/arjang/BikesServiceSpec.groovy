package com.arjang

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BikesServiceSpec extends Specification {

    BikesService bikesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Bikes(...).save(flush: true, failOnError: true)
        //new Bikes(...).save(flush: true, failOnError: true)
        //Bikes bikes = new Bikes(...).save(flush: true, failOnError: true)
        //new Bikes(...).save(flush: true, failOnError: true)
        //new Bikes(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //bikes.id
    }

    void "test get"() {
        setupData()

        expect:
        bikesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Bikes> bikesList = bikesService.list(max: 2, offset: 2)

        then:
        bikesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        bikesService.count() == 5
    }

    void "test delete"() {
        Long bikesId = setupData()

        expect:
        bikesService.count() == 5

        when:
        bikesService.delete(bikesId)
        sessionFactory.currentSession.flush()

        then:
        bikesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Bikes bikes = new Bikes()
        bikesService.save(bikes)

        then:
        bikes.id != null
    }
}
