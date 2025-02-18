package com.arjang

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DbtestServiceSpec extends Specification implements ServiceUnitTest<DbtestService> {

     void "test something"() {
        expect:
        service.doSomething()
     }
}
