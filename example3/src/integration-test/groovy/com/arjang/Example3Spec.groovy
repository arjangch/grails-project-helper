package com.arjang
import grails.plugin.geb.ContainerGebSpec
import grails.testing.mixin.integration.Integration

/**
 * See https://docs.grails.org/latest/guide/testing.html#functionalTesting and https://www.gebish.org/manual/current/
 * for more instructions on how to write functional tests with Grails and Geb.
 */
@Integration
class Example3Spec extends ContainerGebSpec {

    // TODO
//    void 'should display the correct title on the home page'() {
//        when: 'visiting the home page'
//            go '/example3/'
//
//        then: 'the page title is correct'
//            title == 'Welcome to Grails'
//    }

    def "should verify Google homepage title"() {
        when: "I navigate to Google homepage"
        go "https://www.google.com"

        then: "the page title should be 'Google'"
        title == "Google"
    }

}
