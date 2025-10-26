package com.arjang

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent

class MySecurityEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    void onApplicationEvent(AuthenticationSuccessEvent event) {
        // handle the event
        println "---MySecurityEventListener---"
    }
}
