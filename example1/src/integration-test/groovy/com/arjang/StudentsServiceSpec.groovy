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
        new Students(name:'nan1', lastname: 'appie', studentId: 5434).save(flush: true, failOnError: true)
        new Students(name:'nan1', lastname: 'appie', studentId: 5434).save(flush: true, failOnError: true)
        Students students = new Students(name:'nan1', lastname: 'appie', studentId: 5434).save(flush: true, failOnError: true)
        new Students(name:'nan1', lastname: 'appie', studentId: 5434).save(flush: true, failOnError: true)
        new Students(name:'nan1', lastname: 'appie', studentId: 5434).save(flush: true, failOnError: true)
        students.id
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
        Students students = new Students(name:'nan1', lastname: 'appie', studentId: 5434)
        studentsService.save(students)

        then:
        students.id != null
    }
}
