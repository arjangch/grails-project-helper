// 12. URL Properties
grails.plugin.springsecurity.apf.postOnly = false
grails.plugin.springsecurity.logout.postOnly = true

// overriding values in DefaultSecurityConfig.groovy from the SpringSecurityCore 2.0-RC4
grails.plugin.springsecurity.secureChannel.useHeaderCheckChannelSecurity = true
grails.plugin.springsecurity.portMapper.httpPort = 8083
grails.plugin.springsecurity.portMapper.httpsPort = 443

// To change login page see this page https://guides.grails.org/grails-spring-security-core-plugin-custom-authentication/guide/index.html

// 4.1. Pessimistic Lockdown
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.fii.rejectPublicInvocations = false

// 6. Events
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.springsecurity.onAuthenticationSuccessEvent = { e, appCtx ->
    println "---Login success----"
}
grails.plugin.springsecurity.onAuthenticationSwitchUserEvent = { e, appCtx ->
    println "---switch user success----"
}

// 8.3. Remember-Me Cookie - Adds remember me
grails.plugin.springsecurity.rememberMe.cookieName = 'example3_remember_me'
grails.plugin.springsecurity.rememberMe.alwaysRemember = false
grails.plugin.springsecurity.rememberMe.tokenValiditySeconds = 3600
grails.plugin.springsecurity.rememberMe.parameter = 'remember-me'
grails.plugin.springsecurity.rememberMe.useSecureCookie = true
grails.plugin.springsecurity.rememberMe.createSessionOnSuccess = true

// 14. Switch User
grails.plugin.springsecurity.useSwitchUserFilter = true

// 3. Domain Classes - Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.arjang.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.arjang.UserRole'
grails.plugin.springsecurity.authority.className = 'com.arjang.Role'

/* (A)
  DB Request Map
  If you want Requestmap to be stored in DB Comment out static part
  see issue https://github.com/apache/grails-spring-security/issues/1179
 */
grails.plugin.springsecurity.requestMap.className = 'com.arjang.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'

/* (C) TODO
 4.5. Static Map
 I am not sure how to use this
 Issue https://github.com/apache/grails-spring-security/issues/1178
 */
//grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

//4.4. Defining Secured Annotations
//grails.plugin.springsecurity.securityConfigType = "Annotation"

/* (B)
   4.4.2. controllerAnnotations.staticRules
   Static Request Map
   Uncomment if you want request map to be managed statically.  */
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//        [pattern: '/', access: ['permitAll']],
//        [pattern: '/error', access: ['permitAll']],
//        [pattern: '/index', access: ['permitAll']],
//        [pattern: '/index.gsp', access: ['permitAll']],
//        [pattern: '/shutdown', access: ['permitAll']],
//        [pattern: '/assets/**', access: ['permitAll']],
//        [pattern: '/login', access: ['permitAll']],
//        [pattern: '/login/**', access: ['permitAll']],
//        [pattern: '/logout', access: ['permitAll']],
//        [pattern: '/logout/**', access: ['permitAll']],
//        [pattern: '/h2-console/**', access: ['ROLE_ADMIN']],
//        [pattern: '/student/**', access: ['permitAll']],
//        [pattern: '/cars/**', access: ['ROLE_ADMIN']],
//        [pattern: '/teacher/**', access: ['ROLE_ADMIN']]
//]

// 15.3. chainMap
//grails.plugin.springsecurity.filterChain.chainMap = [
//        [pattern: '/assets/**', filters: 'none'],
//        [pattern: '/**/js/**', filters: 'none'],
//        [pattern: '/**/css/**', filters: 'none'],
//        [pattern: '/**/images/**', filters: 'none'],
//        [pattern: '/**/favicon.ico', filters: 'none'],
//        [pattern: '/**', filters: 'JOINED_FILTERS']
//]

// TODO:
//grails.plugin.springsecurity.ipRestrictions = [
//        [pattern: '/pattern1/**', access: '123.234.345.456'],
//        [pattern: '/pattern2/**', access: '10.0.0.0/8'],
//        [pattern: '/pattern3/**', access: ['10.10.200.42', '10.10.200.63']]
//]



