grails.plugin.springsecurity.apf.postOnly = false
grails.plugin.springsecurity.logout.postOnly = true

// overriding values in DefaultSecurityConfig.groovy from the SpringSecurityCore 2.0-RC4
grails.plugin.springsecurity.secureChannel.useHeaderCheckChannelSecurity = true
grails.plugin.springsecurity.portMapper.httpPort = 80
grails.plugin.springsecurity.portMapper.httpsPort = 443

// To change login page see this page https://guides.grails.org/grails-spring-security-core-plugin-custom-authentication/guide/index.html

// Adds remember me
grails.plugin.springsecurity.rememberMe.cookieName='example3_remember_me'
grails.plugin.springsecurity.rememberMe.alwaysRemember=false
grails.plugin.springsecurity.rememberMe.tokenValiditySeconds=1209600
grails.plugin.springsecurity.rememberMe.parameter='remember-me'
grails.plugin.springsecurity.rememberMe.useSecureCookie=true
grails.plugin.springsecurity.rememberMe.createSessionOnSuccess=true

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.arjang.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.arjang.UserRole'
grails.plugin.springsecurity.authority.className = 'com.arjang.Role'
grails.plugin.springsecurity.requestMap.className = 'com.arjang.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//	[pattern: '/',               access: ['permitAll']],
//	[pattern: '/error',          access: ['permitAll']],
//	[pattern: '/index',          access: ['permitAll']],
//	[pattern: '/index.gsp',      access: ['permitAll']],
//	[pattern: '/shutdown',       access: ['permitAll']],
//	[pattern: '/assets/**',      access: ['permitAll']],
//	[pattern: '/**/js/**',       access: ['permitAll']],
//	[pattern: '/**/css/**',      access: ['permitAll']],
//	[pattern: '/**/images/**',   access: ['permitAll']],
//	[pattern: '/**/favicon.ico', access: ['permitAll']]
//]
//
//grails.plugin.springsecurity.filterChain.chainMap = [
//	[pattern: '/assets/**',      filters: 'none'],
//	[pattern: '/**/js/**',       filters: 'none'],
//	[pattern: '/**/css/**',      filters: 'none'],
//	[pattern: '/**/images/**',   filters: 'none'],
//	[pattern: '/**/favicon.ico', filters: 'none'],
//	[pattern: '/**',             filters: 'JOINED_FILTERS']
//]

