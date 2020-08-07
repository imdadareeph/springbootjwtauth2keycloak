package com.imdadareeph.springbootjwtauth2keycloak.config;

import com.imdadareeph.springbootjwtauth2keycloak.model.LoginModel;
import com.imdadareeph.springbootjwtauth2keycloak.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author imdadareeph
 * SecurityContextUtils is used to get username and roles.
 */
@Component
public class SecurityContextUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityContextUtils.class);

    private static final String ANONYMOUS = "imdadareeph";

    private static UserDetails userDetails;

    private SecurityContextUtils() {
    }

    public static String getUserName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = ANONYMOUS;

        if (null != authentication) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                username = springSecurityUser.getUsername();

            } else if (authentication.getPrincipal() instanceof String) {
                username = (String) authentication.getPrincipal();

            } else {
                LOGGER.debug("User details not found in Security Context");
            }
        } else {
            LOGGER.debug("Request not authenticated, hence no user name available");
        }

        return username;
    }

    public static Set<String> getUserRoles() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Set<String> roles = new HashSet<>();

        if (null != authentication) {
            authentication.getAuthorities()
                    .forEach(e -> roles.add(e.getAuthority()));
        }
        return roles;
    }

    public static UserDetails getUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = ANONYMOUS;
        getUserAuthentication(authentication, username);
        return userDetails;
    }

    public static UserDetails getUserDetails(LoginModel loginModel) {
        getUserAuthentication(SecurityContextHolder.getContext().getAuthentication(), loginModel.getUsername());
        return userDetails;
    }

    private static UserDetails getUserAuthentication(Authentication authentication, String username) {
        if (null != authentication) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                username = springSecurityUser.getUsername();
                userDetails= UserDetails.builder().username(username).isAuthenticated(true).description("SecurityContext UserDetails").build();

            } else if (authentication.getPrincipal() instanceof String) {
                username = (String) authentication.getPrincipal();
                userDetails= UserDetails.builder().username(username).isAuthenticated(true).description("SecurityContext String").build();

            } else {
                userDetails= UserDetails.builder().username(username).isAuthenticated(true).description("User details authticated via jwt token").build();
                LOGGER.debug("User details not found in Security Context");
            }
        } else {
            userDetails= UserDetails.builder().username(username).isAuthenticated(false).description("Request not authenticated, hence no user name available").build();
            LOGGER.debug("Request not authenticated, hence no user name available");
        }
        return userDetails;
    }
}