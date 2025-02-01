package com.arjang

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class StudentsSpec extends Specification implements DomainUnitTest<Students> {

     void "test domain constraints"() {
        when:
        Students domain = new Students()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
