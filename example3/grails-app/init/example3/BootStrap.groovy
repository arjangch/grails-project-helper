package example3

import com.arjang.Student
import com.arjang.Teacher
import com.arjang.Cars
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
//        addInitUsers()
//        addInitAccess()
//        addInitData()
    }

    def destroy = {
    }

    @Transactional
    def addInitUsers() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Toronto"))

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def supportRole = Role.findByAuthority('ROLE_SUPPORT') ?: new Role(authority: 'ROLE_SUPPORT').save(failOnError: true)
        def readonlyRole = Role.findByAuthority('ROLE_READONLY') ?: new Role(authority: 'ROLE_READONLY').save(failOnError: true)

        // admin
        def adminUser = User.findByUsername(grailsApplication.config.exampleUser.adminUser) ?: new User(
                username: grailsApplication.config.exampleUser.adminUser,
                password: grailsApplication.config.exampleUser.adminPwd,
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            UserRole.create adminUser, adminRole
        }

        // support
        def supportUser = User.findByUsername(grailsApplication.config.exampleUser.supportUser) ?: new User(
                username: grailsApplication.config.exampleUser.supportUser,
                password: grailsApplication.config.exampleUser.supportPwd,
                enabled: true).save(failOnError: true)

        if (!supportUser.authorities.contains(supportRole)) {
            UserRole.create supportUser, supportRole
        }

        // readOnly
        def readOnlyUser = User.findByUsername(grailsApplication.config.exampleUser.readOnlyUser) ?: new User(
                username: grailsApplication.config.exampleUser.readOnlyUser,
                password: grailsApplication.config.exampleUser.readOnlyPwd,
                enabled: true).save(failOnError: true)

        if (!readOnlyUser.authorities.contains(readonlyRole)) {
            UserRole.create readOnlyUser, readonlyRole
        }
    }

    @Transactional
    def addInitAccess() {
        for (String url in [
                '/',
                '/error',
                '/index',
                '/index.gsp',
                '/**/favicon.ico',
                '/assets/**',
                '/robots.txt',
                '/alive',
                '/login/auth',
                '/login/index',
                '/logout/index',
                '/student/**']) {
            new Requestmap(url: url, configAttribute: 'permitAll', 'httpMethod': 'GET').save(failOnError: true)
        }

        for (String url in [
                '/login/index',
                '/logout/index',
                '/student/**']) {
            new Requestmap(url: url, configAttribute: 'permitAll', 'httpMethod': 'POST').save(failOnError: true)
        }

        for (String url in [
                '/h2-console/**',
                '/teacher/**',
                '/user/**',
                '/role/**',
                '/userRole/**',
                '/registrationCode/**',
                '/securityInfo/**',
                '/requestmap/**']) {
            new Requestmap(url: url, configAttribute: 'ROLE_ADMIN').save(failOnError: true)
        }

        // Reload RequestMap changes
        springSecurityService.clearCachedRequestmaps()

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



    }


}
