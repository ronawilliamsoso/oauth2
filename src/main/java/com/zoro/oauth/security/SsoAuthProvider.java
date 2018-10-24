package com.zoro.oauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

 
@Component
public class SsoAuthProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(SsoAuthProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("costomerized provider call");

        // return a Token to show the success of login
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.<GrantedAuthority>emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
