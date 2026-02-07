package example3

import com.arjang.*
import jakarta.servlet.ServletContext
import com.arjang.Role
import com.arjang.User
import com.arjang.UserRole
import com.arjang.Requestmap
import grails.gorm.transactions.Transactional

class BootStrap {
    def grailsApplication
    def springSecurityService
    ServletContext servletContext

    def init = {
        addInitUsers()
        addInitData()
        addInitAccess()
        domainAccess()
    }

    def destroy = {
    }

    @Transactional
    def addInitUsers() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Toronto"))

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
        def supportRole = Role.findByAuthority('ROLE_SUPPORT') ?: new Role(authority: 'ROLE_SUPPORT').save()
        def readonlyRole = Role.findByAuthority('ROLE_READONLY') ?: new Role(authority: 'ROLE_READONLY').save()
        def supervisorRole = Role.findByAuthority('ROLE_SUPERVISOR') ?: new Role(authority: 'ROLE_SUPERVISOR').save()

        def switchRole = Role.findByAuthority('ROLE_SWITCH_USER') ?: new Role(authority: 'ROLE_SWITCH_USER').save()

        // admin
        def adminUser = User.findByUsername(grailsApplication.config.exampleUser.adminUser) ?: new User(
                username: grailsApplication.config.exampleUser.adminUser,
                password: grailsApplication.config.exampleUser.adminPwd,
                enabled: true).save()

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }

        if (!adminUser.authorities.contains(switchRole)) {
            UserRole.create adminUser, switchRole
        }

        // support
        def supportUser = User.findByUsername(grailsApplication.config.exampleUser.supportUser) ?: new User(
                username: grailsApplication.config.exampleUser.supportUser,
                password: grailsApplication.config.exampleUser.supportPwd,
                enabled: true).save()

        if (!supportUser.authorities.contains(supportRole)) {
            UserRole.create supportUser, supportRole
        }

        // readOnly
        def readOnlyUser = User.findByUsername(grailsApplication.config.exampleUser.readOnlyUser) ?: new User(
                username: grailsApplication.config.exampleUser.readOnlyUser,
                password: grailsApplication.config.exampleUser.readOnlyPwd,
                enabled: true).save()

        if (!readOnlyUser.authorities.contains(readonlyRole)) {
            UserRole.create readOnlyUser, readonlyRole
        }

        def supervisorUser = User.findByUsername(grailsApplication.config.exampleUser.supervisorUser) ?: new User(
                username: grailsApplication.config.exampleUser.supervisorUser,
                password: grailsApplication.config.exampleUser.supervisorPwd,
                enabled: true).save()

        if (!supervisorUser.authorities.contains(supervisorRole)) {
            UserRole.create supervisorUser, supervisorRole
            UserRole.create supervisorUser, supportRole
            UserRole.create supervisorUser, switchRole
        }

    }

    @Transactional
    def addInitAccess() {
        for (String url in [
                '/',
                '/login/auth',
                '/error',
                '/index',
                '/index.gsp',
                '/testSpringTags',
                '/**/favicon.ico',
                '/assets/**',
                '/robots.txt',
                '/alive',
                '/loginError']) {
            new Requestmap(url: url, configAttribute: 'permitAll', 'httpMethod': 'GET').save()
        }

        for (String url in [
                '/login/index',
                '/logout/index',
                '/logout/impersonate']) {
            new Requestmap(url: url, configAttribute: 'permitAll', 'httpMethod': 'POST').save()
        }

        for (String url in[
                "/login/authfail"
        ]){
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }

        for (String url in [
                '/login/impersonate']){ new Requestmap(url: url ,configAttribute: 'ROLE_SWITCH_USER,IS_AUTHENTICATED_FULLY').save()
        }

        for (String url in [
                '/h2-console/**',
                '/user/**',
                '/role/**',
                '/userRole/**',
                '/registrationCode/**',
                '/securityInfo/**',
                '/requestmap/**']) {
            new Requestmap(url: url, configAttribute: 'ROLE_ADMIN').save()
        }

    }

    @Transactional
    def addInitData() {
        new Student(name: "sName1", lastname: "sLastname1", grade: "A1", studentId: "11").save()
        new Student(name: "sName2", lastname: "sLastname2", grade: "A2", studentId: "12").save()
        new Student(name: "sName3", lastname: "sLastname3", grade: "A3", studentId: "13").save()

        new Teacher(name: "tName1", lastname: "tLastName1", teacherId: 911).save()
        new Teacher(name: "tName2", lastname: "tLastName2", teacherId: 922).save()
        new Teacher(name: "tName3", lastname: "tLastName3", teacherId: 933).save()

        new Cars(name: "BMW", color: "Red", doors: "4").save()
        new Cars(name: "VW", color: "Blue", doors: "4").save()
        new Cars(name: "Ford", color: "white", doors: "2").save()

        new Bikes(brand: "CN", height: 17).save()
        new Bikes(brand: "BNC", height: 21).save()
        new Bikes(brand: "BB", height: 22).save()

        new Suvs(name :"Taj", brand: "VW", color: "Blue").save()
        new Suvs(name :"Despra", brand: "BMW", color: "Green").save()
        new Suvs(name :"Fiest", brand: "Ford", color: "Black").save()
        new Suvs(name :"Cool", brand: "BYD", color: "White").save()
    }

    @Transactional
    def domainAccess(){

        for (String url in[
                "/student/**"
        ]){
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }

        for (String url in[
                "/bikes/**"
        ]){
            new Requestmap(url: url, configAttribute: 'ROLE_ADMIN').save()
        }

        for (String url in[
                "/teacher",
                "/teacher/show/**"
        ]){
            new Requestmap(url: url, configAttribute: 'ROLE_ADMIN,ROLE_SUPPORT').save()
        }

        for (String url in[
                "/cars/show/**",
                "/cars/edit/**",
                "/cars/update/**",
                "/cars/create/**"
        ]){
            new Requestmap(url: url, configAttribute: 'ROLE_SUPERVISOR').save()
        }

        for (String url in[
                "/cars",
                "/cars/index"
        ]){
            new Requestmap(url: url, configAttribute: 'ROLE_SUPPORT, ROLE_SUPERVISOR').save()
        }

        for (String url in[
                "/suvs",
                "/suvs/show/**"
        ]){
            new Requestmap(url: url, configAttribute: 'ROLE_READONLY').save()
        }



        // different way of adding permission.
        // also it removes show from permitAll
//         new Requestmap(url: "/bikes/show/*", configAttribute: "authentication.name == 'admin'").save()

        // new Requestmap(url: "/cars/show/*", configAttribute: "hasRole('ROLE_SUPPORT')").save()

        // Reload RequestMap changes
        springSecurityService.clearCachedRequestmaps()

    }

}
