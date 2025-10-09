package com.arjang

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CarsSpec extends Specification implements DomainUnitTest<Cars> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
