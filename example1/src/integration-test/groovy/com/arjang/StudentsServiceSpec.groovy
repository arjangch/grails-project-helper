package com.arjang

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class StudentsServiceSpec extends Specification {

    StudentsService studentsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Students(...).save(flush: true, failOnError: true)
        //new Students(...).save(flush: true, failOnError: true)
        //Students students = new Students(...).save(flush: true, failOnError: true)
        //new Students(...).save(flush: true, failOnError: true)
        //new Students(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //students.id
    }

    void "test get"() {
        setupData()

        expect:
        studentsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Students> studentsList = studentsService.list(max: 2, offset: 2)

        then:
        studentsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        studentsService.count() == 5
    }

    void "test delete"() {
        Long studentsId = setupData()

        expect:
        studentsService.count() == 5

        when:
        studentsService.delete(studentsId)
        sessionFactory.currentSession.flush()

        then:
        studentsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Students students = new Students()
        studentsService.save(students)

        then:
        students.id != null
    }
}
