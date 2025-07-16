package com.nelson

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CliTestSpec extends Specification implements DomainUnitTest<CliTest> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
