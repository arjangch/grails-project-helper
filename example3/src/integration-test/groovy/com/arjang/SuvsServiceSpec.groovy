package com.arjang

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SuvsServiceSpec extends Specification {

    SuvsService suvsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Suvs(...).save(flush: true, failOnError: true)
        //new Suvs(...).save(flush: true, failOnError: true)
        //Suvs suvs = new Suvs(...).save(flush: true, failOnError: true)
        //new Suvs(...).save(flush: true, failOnError: true)
        //new Suvs(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //suvs.id
    }

    void "test get"() {
        setupData()

        expect:
        suvsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Suvs> suvsList = suvsService.list(max: 2, offset: 2)

        then:
        suvsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        suvsService.count() == 5
    }

    void "test delete"() {
        Long suvsId = setupData()

        expect:
        suvsService.count() == 5

        when:
        suvsService.delete(suvsId)
        sessionFactory.currentSession.flush()

        then:
        suvsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Suvs suvs = new Suvs()
        suvsService.save(suvs)

        then:
        suvs.id != null
    }
}
