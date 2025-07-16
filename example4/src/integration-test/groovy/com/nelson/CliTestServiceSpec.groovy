package com.nelson

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CliTestServiceSpec extends Specification {

    CliTestService cliTestService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new CliTest(...).save(flush: true, failOnError: true)
        //new CliTest(...).save(flush: true, failOnError: true)
        //CliTest cliTest = new CliTest(...).save(flush: true, failOnError: true)
        //new CliTest(...).save(flush: true, failOnError: true)
        //new CliTest(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cliTest.id
    }

    void "test get"() {
        setupData()

        expect:
        cliTestService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<CliTest> cliTestList = cliTestService.list(max: 2, offset: 2)

        then:
        cliTestList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        cliTestService.count() == 5
    }

    void "test delete"() {
        Long cliTestId = setupData()

        expect:
        cliTestService.count() == 5

        when:
        cliTestService.delete(cliTestId)
        sessionFactory.currentSession.flush()

        then:
        cliTestService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        CliTest cliTest = new CliTest()
        cliTestService.save(cliTest)

        then:
        cliTest.id != null
    }
}
